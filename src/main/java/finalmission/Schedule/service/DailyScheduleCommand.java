package finalmission.Schedule.service;

import finalmission.Schedule.model.DailySchedule;
import finalmission.Schedule.model.ReservationTime;
import finalmission.Schedule.repository.DailyScheduleRepository;
import finalmission.member.model.Trainer;
import java.time.DayOfWeek;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Transactional
public class DailyScheduleCommand {

    private final DailyScheduleRepository dailyScheduleRepository;

    public DailySchedule createDailySchedule(
            final ReservationTime time,
            final Trainer trainer,
            final DayOfWeek dayOfWeek
    ) {
        DailySchedule dailySchedule = DailySchedule.builder()
                .dayOfWeek(dayOfWeek)
                .time(time)
                .trainer(trainer)
                .build();
        return dailyScheduleRepository.save(dailySchedule);
    }


}
