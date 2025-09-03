package finalmission.schedule.controller;

import finalmission.member.model.Trainer;
import finalmission.member.service.TrainerQuery;
import finalmission.schedule.controller.dto.ModifyScheduleRequest;
import finalmission.schedule.controller.dto.ScheduleResponse;
import finalmission.schedule.model.ReservationDate;
import finalmission.schedule.model.ReservationTime;
import finalmission.schedule.model.Schedule;
import finalmission.schedule.service.ScheduleCommand;
import finalmission.schedule.service.ScheduleQuery;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ScheduleService {

    private final ScheduleQuery scheduleQuery;
    private final TrainerQuery trainerQuery;
    private final ScheduleCommand scheduleCommand;

    public List<ScheduleResponse> findAllScheduleByTrainerIdAndDate(final Long trainerId, final LocalDate date) {
        Trainer trainer = trainerQuery.getTrainerById(trainerId);
        List<Schedule> schedules = scheduleQuery.findAllScheduleByTrainerAndDate(trainer, new ReservationDate(date));
        return ScheduleResponse.from(schedules);
    }

    public void modifySchedule(
            final Long trainerId,
            final LocalDate date,
            final LocalTime time,
            final ModifyScheduleRequest request
    ) {
        Trainer trainer = trainerQuery.getTrainerById(trainerId);
        Schedule schedule = scheduleQuery.getSchedule(trainer, new ReservationDate(date), new ReservationTime(time));
        scheduleCommand.changeReservationTime(schedule, new ReservationTime(request.modifyTime()));
    }

    public void deleteSchedule(final Long trainerId, final LocalDate date, final LocalTime time) {
        Trainer trainer = trainerQuery.getTrainerById(trainerId);
        Schedule schedule = scheduleQuery.getSchedule(trainer, new ReservationDate(date), new ReservationTime(time));
        scheduleCommand.delete(schedule);
    }
}
