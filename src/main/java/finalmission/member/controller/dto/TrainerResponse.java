package finalmission.member.controller.dto;

import finalmission.member.model.Trainer;
import java.util.List;

public record TrainerResponse(
        Long id,
        String name
) {
    public static TrainerResponse from(Trainer trainer) {
        return new TrainerResponse(trainer.getId(), trainer.getName().name());
    }

    public static List<TrainerResponse> from(List<Trainer> trainers) {
        return trainers.stream()
                .map(TrainerResponse::from)
                .toList();
    }
}
