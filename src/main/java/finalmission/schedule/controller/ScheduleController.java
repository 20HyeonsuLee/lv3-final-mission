package finalmission.schedule.controller;

import finalmission.schedule.controller.dto.ModifyScheduleRequest;
import finalmission.schedule.controller.dto.ScheduleResponse;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/schedules")
public class ScheduleController {
    private final ScheduleService scheduleService;

    @GetMapping("/{trainer-id}/{date}")
    public ResponseEntity<List<ScheduleResponse>> findAllSchedule(
            @PathVariable("trainer-id") final Long trainerId,
            @PathVariable("date") final LocalDate date
    ) {
        return ResponseEntity.ok(scheduleService.findAllScheduleByTrainerIdAndDate(trainerId, date));
    }

    @PostMapping("/{trainer-id}/{date}/{time}")
    public ResponseEntity<Void> modifySchedule(
            @PathVariable("trainer-id") final Long trainerId,
            @PathVariable("date") final LocalDate date,
            @PathVariable("time") final LocalTime time,
            @RequestBody ModifyScheduleRequest request
    ) {
        scheduleService.modifySchedule(trainerId, date, time, request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{trainer-id}/{date}/{time}")
    public ResponseEntity<Void> deleteSchedule(
            @PathVariable("trainer-id") final Long trainerId,
            @PathVariable("date") final LocalDate date,
            @PathVariable("time") final LocalTime time
    ) {
        scheduleService.deleteSchedule(trainerId, date, time);
        return ResponseEntity.noContent().build();
    }
}
