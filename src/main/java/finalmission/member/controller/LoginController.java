package finalmission.member.controller;

import finalmission.member.controller.dto.LoginRequest;
import finalmission.member.model.member.Member;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/login")
public class LoginController {
    private final MemberService memberService;

    @PostMapping
    public ResponseEntity<Void> login(@RequestBody @Valid final LoginRequest request, final HttpSession session) {
        Member member = memberService.login(request);
        session.setAttribute("LOGIN_MEMBER", member);
        return ResponseEntity.ok().build();
    }
}
