package finalmission.Schedule.service;

import finalmission.Schedule.model.ReservationTime;
import finalmission.Schedule.model.Schedule;
import finalmission.Schedule.repository.ScheduleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Transactional
public class ScheduleCommand {

    private final ScheduleRepository scheduleRepository;
    private final ScheduleQuery scheduleQuery;

    public void changeReservationTime(Schedule schedule, ReservationTime time) {
        scheduleQuery.getReservationTime(time);
        schedule.changeTime(time);
    }
}
