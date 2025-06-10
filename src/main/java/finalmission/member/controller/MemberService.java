package finalmission.member.controller;

import finalmission.member.service.MemberQuery;
import finalmission.member.controller.dto.LoginRequest;
import finalmission.member.model.member.Email;
import finalmission.member.model.member.Member;
import finalmission.member.model.member.Password;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MemberService {

    private final MemberQuery memberQuery;

    public Member login(final LoginRequest request) {
        return memberQuery.getByEmailAndPassword(
                new Email(request.email()),
                new Password(request.password())
        );
    }
}
