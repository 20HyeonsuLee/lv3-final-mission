package finalmission.member.controller;

import finalmission.member.controller.dto.TrainerResponse;
import finalmission.member.service.MemberQuery;
import finalmission.member.controller.dto.LoginRequest;
import finalmission.member.model.member.Email;
import finalmission.member.model.member.Member;
import finalmission.member.model.member.Password;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MemberService {

    private final MemberQuery memberQuery;
    private final RandomNameClient randomNameClient;

    public Member login(final LoginRequest request) {
        return memberQuery.getByEmailAndPassword(
                new Email(request.email()),
                new Password(request.password())
        );
    }

    public List<TrainerResponse> findAllTrainer() {
        return TrainerResponse.from(memberQuery.findAllTrainer());
    }

    public String getComment() {
        String randomName = randomNameClient.getRandomName();
        return String.format("%s: 이분한테 PT받고 몸짱됐어요~", randomName);

    }
}
