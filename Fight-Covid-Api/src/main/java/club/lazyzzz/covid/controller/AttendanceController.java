package club.lazyzzz.covid.controller;

import club.lazyzzz.covid.core.Response;
import club.lazyzzz.covid.model.entity.Attendance;
import club.lazyzzz.covid.service.AttendanceService;
import club.lazyzzz.covid.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/attendance")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;
    @Autowired
    private JwtService jwtService;

    @GetMapping
    public Response list(@RequestParam String token) {
        Long userId = getUserId(token);
        List<Attendance> attendances = attendanceService.list(userId);
        return Response.ok("签到查询成功", attendances);
    }

    @PostMapping
    public Response singIn(@RequestParam String token, @RequestBody Attendance attendance) {
        Long userId = getUserId(token);
        attendance.setUserId(userId);
        attendanceService.singIn(attendance);
        return Response.ok("签到成功");
    }

    private Long getUserId(String token) {
        return jwtService.verify(token).getClaims().get("userId").asLong();
    }
}
