package finalmission.reservation.controller;

import finalmission.reservation.controller.dto.ReservationRequest;
import finalmission.reservation.controller.dto.ReservationResponse;
import finalmission.member.model.Customer;
import jakarta.validation.Valid;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/reservations")
public class ReservationController {
    private final ReservationService memberService;

    @PostMapping
    public ResponseEntity<Void> createReservation(
            @RequestBody @Valid final ReservationRequest request,
            final Customer customer
    ) {
        memberService.createReservation(request, customer);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReservation(
            @PathVariable("id") Long id,
            final Customer customer
    ) {
        memberService.deleteById(id, customer);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/mine")
    public ResponseEntity<List<ReservationResponse>> findAllMyReservations(final Customer customer) {
        List<ReservationResponse> response = memberService.findAllMyReservation(customer);
        return ResponseEntity.ok(response);
    }
}
