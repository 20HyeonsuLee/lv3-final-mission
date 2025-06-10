package finalmission.Schedule.controller;

import finalmission.Schedule.controller.dto.ScheduleResponse;
import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
}
