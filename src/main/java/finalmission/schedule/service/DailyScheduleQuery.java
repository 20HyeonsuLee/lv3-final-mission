package finalmission.schedule.service;

import finalmission.schedule.model.DailySchedule;
import finalmission.schedule.repository.DailyScheduleRepository;
import java.util.NoSuchElementException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class DailyScheduleQuery {

    private final DailyScheduleRepository dailyScheduleRepository;

    public DailySchedule getDailySchedule(final Long dailyScheduleId) {
        return dailyScheduleRepository.findById(dailyScheduleId)
                .orElseThrow(() -> new NoSuchElementException("존재하지 않는 요일별 일정입니다."));
    }
}
