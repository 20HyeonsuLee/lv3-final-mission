package finalmission.Schedule.model;

import jakarta.persistence.Embeddable;
import java.time.LocalDate;

@Embeddable
public record ReservationDate(LocalDate date) {
}
