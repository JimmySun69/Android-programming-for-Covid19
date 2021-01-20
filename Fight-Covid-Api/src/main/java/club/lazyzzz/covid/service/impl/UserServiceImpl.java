package club.lazyzzz.covid.service.impl;

import club.lazyzzz.covid.model.entity.User;
import club.lazyzzz.covid.repository.UserRepository;
import club.lazyzzz.covid.service.JwtService;
import club.lazyzzz.covid.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final JwtService jwtService;

    public UserServiceImpl(UserRepository userRepository, JwtService jwtService) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }

    @Override
    public String login(String username, String plainPassword) {
        final User user;

        String mismatchTip = "用户名或者密码不正确";

        user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException(mismatchTip));
        if (!user.getPassword().equals(plainPassword)) {
            throw new RuntimeException(mismatchTip);
        }

        return jwtService.generator(user.getId());
    }

    @Override
    public void register(User user) {
        userRepository.findByUsername(user.getUsername()).ifPresent(existedUser -> {
            throw new RuntimeException("用户名已经存在");
        });
        userRepository.save(user);
    }
}
