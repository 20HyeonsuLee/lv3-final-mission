package finalmission.member.model.member;

import jakarta.persistence.Embeddable;

@Embeddable
public record Email(String email) {

    @Override
    public String toString() {
        return email;
    }
}
