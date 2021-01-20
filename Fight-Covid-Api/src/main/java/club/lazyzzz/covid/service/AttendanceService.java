package club.lazyzzz.covid.service;

import club.lazyzzz.covid.model.entity.Attendance;

import java.util.List;

public interface AttendanceService {
    /**
     * sign in the attendance
     * @param attendance attendance object
     */
    void singIn(Attendance attendance);

    /**
     * List Attendance History by user ID
     * @param userId userId
     */
    List<Attendance> list(Long userId);
}
