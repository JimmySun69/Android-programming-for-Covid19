package club.lazyzzz.covid.controller;

import club.lazyzzz.covid.core.Response;
import club.lazyzzz.covid.model.entity.User;
import club.lazyzzz.covid.repository.UserRepository;
import club.lazyzzz.covid.service.JwtService;
import club.lazyzzz.covid.service.UserService;
import club.lazyzzz.covid.util.UpdateUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "User Management Interface")
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtService jwtService;

    @GetMapping
    @ApiOperation("Query user information via token")
    public Response info(@RequestParam String token) {
        Long userId = getUserIdByToken(token);
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User Not Found"));
        user.setPassword(null);
        return Response.ok("用户信息查询成功", user);
    }

    @PutMapping
    @ApiOperation("Update user information via token")
    public Response updateInfo(@RequestParam String token, @RequestBody User user) {
        Long userId = getUserIdByToken(token);
        User original = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User Not Found"));
        BeanUtils.copyProperties(user, original, UpdateUtil.getNullPropertyNames(user));
        User saved = userRepository.save(original);
        saved.setPassword(null);
        return Response.ok("用户信息更新成功", saved);
    }

    @PostMapping("/login")
    @ApiOperation("User login interface")
    public Response login(@RequestBody User user) {
        String token = userService.login(user.getUsername(), user.getPassword());
        return Response.ok("登录成功", token);
    }

    @PostMapping("/register")
    @ApiOperation("User register interface")
    public Response register(@RequestBody User user) {
        user.setId(null);
        userService.register(user);
        return Response.ok("注册成功", null);
    }

    private Long getUserIdByToken(String token) {
        return jwtService.verify(token).getClaims().get("userId").asLong();
    }
}
