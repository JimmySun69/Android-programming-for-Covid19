package club.lazyzzz.covid.service.impl;

import club.lazyzzz.covid.model.entity.Attendance;
import club.lazyzzz.covid.repository.AttendanceRepository;
import club.lazyzzz.covid.repository.UserRepository;
import club.lazyzzz.covid.service.AttendanceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AttendanceServiceImpl implements AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final UserRepository userRepository;

    public AttendanceServiceImpl(AttendanceRepository attendanceRepository,
                                 UserRepository userRepository) {
        this.attendanceRepository = attendanceRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public void singIn(Attendance attendance) {
        attendance.setId(null);
        userRepository.findById(attendance.getUserId()).orElseThrow(() -> new RuntimeException("用户id不存在, 签到失败"));
        attendanceRepository.save(attendance);
    }

    @Override
    public List<Attendance> list(Long userId) {
        return attendanceRepository.findByUserId(userId).orElseThrow(() -> new RuntimeException("用户id不存在, 查询失败"));
    }
}
