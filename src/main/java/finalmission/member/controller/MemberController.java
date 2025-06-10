package finalmission.member.controller;

import finalmission.member.controller.dto.LoginRequest;
import finalmission.member.controller.dto.TrainerResponse;
import finalmission.member.model.member.Member;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/members")
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody @Valid final LoginRequest request, final HttpSession session) {
        Member member = memberService.login(request);
        session.setAttribute("LOGIN_MEMBER", member);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/trainers")
    public ResponseEntity<List<TrainerResponse>> findAllTrainer() {
        return ResponseEntity.ok(memberService.findAllTrainer());
    }
}
