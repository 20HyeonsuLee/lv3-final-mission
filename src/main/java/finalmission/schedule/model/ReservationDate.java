package finalmission.schedule.model;

import jakarta.persistence.Embeddable;
import java.time.LocalDate;

@Embeddable
public record ReservationDate(LocalDate date) {
}
