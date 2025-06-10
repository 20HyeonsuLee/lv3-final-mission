package finalmission.member.service;

import finalmission.member.model.Trainer;
import finalmission.member.repository.TrainerRepository;
import java.util.NoSuchElementException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TrainerQuery {

    private final TrainerRepository trainerRepository;

    public Trainer getTrainerById(Long id) {
        return trainerRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("존재하지 않는 트레이너입니다."));
    }
}
