package finalmission.member.model;

import finalmission.member.model.member.Member;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@DiscriminatorValue("TRAINER")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Trainer extends Member {

}
