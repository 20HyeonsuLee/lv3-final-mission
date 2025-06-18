package finalmission.schedule.controller;

import finalmission.member.model.Trainer;
import finalmission.schedule.controller.dto.CreateDailyScheduleRequest;
import finalmission.schedule.controller.dto.CreateDailyScheduleResponse;
import finalmission.schedule.model.DailySchedule;
import finalmission.schedule.model.ReservationTime;
import finalmission.schedule.service.DailyScheduleCommand;
import finalmission.schedule.service.DailyScheduleQuery;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DailyScheduleService {

    private final DailyScheduleCommand dailyScheduleCommand;
    private final DailyScheduleQuery dailyScheduleQuery;

    public CreateDailyScheduleResponse createDailyScheudle(
            final CreateDailyScheduleRequest request,
            final Trainer trainer
    ) {
        DailySchedule dailySchedule = dailyScheduleCommand.createDailySchedule(
                new ReservationTime(request.time()),
                trainer,
                request.dayOfWeek()
        );
        return CreateDailyScheduleResponse.from(dailySchedule);
    }

    public void deleteDailySchedule(final Long id) {
        DailySchedule dailySchedule = dailyScheduleQuery.getDailySchedule(id);
        dailyScheduleCommand.delete(dailySchedule);

    }
}
