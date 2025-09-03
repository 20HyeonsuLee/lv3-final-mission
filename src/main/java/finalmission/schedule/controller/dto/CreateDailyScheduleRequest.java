package finalmission.schedule.controller.dto;

import java.time.DayOfWeek;
import java.time.LocalTime;

public record CreateDailyScheduleRequest(
        DayOfWeek dayOfWeek,
        LocalTime time
) {
}
