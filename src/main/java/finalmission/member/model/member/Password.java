package finalmission.member.model.member;

import jakarta.persistence.Embeddable;

@Embeddable
public record Password(String password) {

    @Override
    public String toString() {
        return password;
    }
}
