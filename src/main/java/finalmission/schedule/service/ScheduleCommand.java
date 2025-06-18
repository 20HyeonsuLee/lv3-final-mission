package finalmission.schedule.service;

import finalmission.schedule.model.ReservationTime;
import finalmission.schedule.model.Schedule;
import finalmission.schedule.repository.ScheduleRepository;
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
