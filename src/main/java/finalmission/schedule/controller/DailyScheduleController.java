package finalmission.schedule.controller;

import finalmission.member.model.Trainer;
import finalmission.schedule.controller.dto.CreateDailyScheduleRequest;
import finalmission.schedule.controller.dto.CreateDailyScheduleResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/daily/schedules")
public class DailyScheduleController {

    private final DailyScheduleService dailyScheduleService;

    @PostMapping
    public ResponseEntity<CreateDailyScheduleResponse> createDailySchedule(
            @RequestBody final CreateDailyScheduleRequest request,
            final Trainer trainer
    ) {
        CreateDailyScheduleResponse response = dailyScheduleService.createDailyScheudle(request, trainer);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDailySchedule(
            @PathVariable("id") final Long id
    ) {
        dailyScheduleService.deleteDailySchedule(id);
        return ResponseEntity.noContent().build();
    }
}
