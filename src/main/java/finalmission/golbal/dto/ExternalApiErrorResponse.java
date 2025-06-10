package finalmission.golbal.dto;

import org.springframework.http.HttpStatus;

public record ExternalApiErrorResponse(
        HttpStatus status,
        String message
) {
}
