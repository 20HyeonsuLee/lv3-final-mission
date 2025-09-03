package finalmission.schedule.model;

import java.time.Clock;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.Getter;

@Getter
public class DateRange {
    private final LocalDate start;
    private final LocalDate end;

    public DateRange(final LocalDate start, final LocalDate end) {
        this.start = start;
        this.end = end;
    }

    public static DateRange createNextMonthRange(Clock clock, int monthCount) {
        LocalDate now = LocalDate.now(clock);
        return new DateRange(now, now.plusMonths(monthCount));
    }

    public Set<LocalDate> getAllDays() {
        return start.datesUntil(end).collect(Collectors.toSet());
    }

    public Set<LocalDate> differenceTo(Set<LocalDate> other) {
        Set<LocalDate> origin = new HashSet<>(getAllDays());
        origin.removeAll(other);
        return origin;
    }
}
