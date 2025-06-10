package finalmission.member.service;

import finalmission.member.model.Trainer;
import finalmission.member.model.member.Email;
import finalmission.member.model.member.Member;
import finalmission.member.model.member.Password;
import finalmission.member.repository.MemberRepository;
import finalmission.member.repository.TrainerRepository;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MemberQuery {

    private final MemberRepository memberRepository;
    private final TrainerRepository trainerRepository;

    public Member getByEmailAndPassword(final Email email, final Password password) {
        return memberRepository.findMemberByEmailAndPassword(email, password)
                .orElseThrow(() -> new NoSuchElementException("존재하지 않는 유저입니다."));
    }

    public Optional<Member> findByEmailAndPassword(final Email email, final Password password) {
        return memberRepository.findMemberByEmailAndPassword(email, password);
    }

    public boolean existsByEmailAndPassword(final Email email, final Password password) {
        return memberRepository.existsByEmailAndPassword(email, password);
    }

    public List<Trainer> findAllTrainer() {
        return trainerRepository.findAll();
    }
}
