package finalmission.Schedule.controller.dto;

import finalmission.Schedule.model.Schedule;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public record ScheduleResponse(
        Long scheduleId,
        LocalDate date,
        LocalTime time
) {
    public static ScheduleResponse from(Schedule schedule) {
        return new ScheduleResponse(
                schedule.getId(),
                schedule.getDate().date(),
                schedule.getTime().time()
        );
    }

    public static List<ScheduleResponse> from(List<Schedule> schedules) {
        return schedules.stream()
                .map(ScheduleResponse::from)
                .toList();
    }
}
