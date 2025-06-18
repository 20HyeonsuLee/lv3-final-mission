package finalmission.schedule.controller;

import finalmission.schedule.controller.dto.ScheduleResponse;
import finalmission.schedule.model.ReservationDate;
import finalmission.schedule.model.Schedule;
import finalmission.schedule.service.ScheduleQuery;
import finalmission.member.model.Trainer;
import finalmission.member.service.TrainerQuery;
import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ScheduleService {

    private final ScheduleQuery scheduleQuery;
    private final TrainerQuery trainerQuery;

    public List<ScheduleResponse> findAllScheduleByTrainerIdAndDate(final Long trainerId, final LocalDate date) {
        Trainer trainer = trainerQuery.getTrainerById(trainerId);
        List<Schedule> schedules = scheduleQuery.findAllScheduleByTrainerAndDate(trainer, new ReservationDate(date));
        return ScheduleResponse.from(schedules);
    }
}
