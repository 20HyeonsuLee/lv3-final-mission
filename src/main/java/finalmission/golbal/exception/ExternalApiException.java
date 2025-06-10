package finalmission.golbal.exception;

import finalmission.golbal.dto.ExternalApiErrorResponse;
import org.springframework.http.HttpStatus;

public class ExternalApiException extends RuntimeException {
    private final ExternalApiErrorResponse errorResponse;

    public ExternalApiException(final ExternalApiErrorResponse errorResponse) {
        super(errorResponse.message());
        this.errorResponse = errorResponse;
    }

    public HttpStatus getStatus() {
        return errorResponse.status();
    }
}
