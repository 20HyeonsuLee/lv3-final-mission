package finalmission.schedule.controller.dto;

import finalmission.schedule.model.DailySchedule;
import java.time.LocalTime;

public record CreateDailyScheduleResponse(
        String trainerName,
        String dayOfWeek,
        LocalTime time
) {
    public static CreateDailyScheduleResponse from(final DailySchedule dailySchedule) {
        return new CreateDailyScheduleResponse(
                dailySchedule.getTrainer().getName().name(),
                dailySchedule.getDayOfWeek().toString(),
                dailySchedule.getTime().time()
        );

    }
}
