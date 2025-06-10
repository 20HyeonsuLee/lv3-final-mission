package finalmission.member.model.member;

import jakarta.persistence.Embeddable;
import java.time.LocalDate;

@Embeddable
public record BirthDate(LocalDate birthDate) {
}
