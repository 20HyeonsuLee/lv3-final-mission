package finalmission.member.model;

import finalmission.member.model.member.BirthDate;
import finalmission.member.model.member.Email;
import finalmission.member.model.member.Gender;
import finalmission.member.model.member.Member;
import finalmission.member.model.member.Name;
import finalmission.member.model.member.Password;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@DiscriminatorValue("MEMBER")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Customer extends Member {

    @Builder
    public Customer(
            final Email email,
            final Password password,
            final Name name,
            final BirthDate birthDate,
            final Gender gender
    ) {
        super(null, email, password, name, birthDate, gender);
    }

}
