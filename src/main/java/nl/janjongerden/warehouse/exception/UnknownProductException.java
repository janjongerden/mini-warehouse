package nl.janjongerden.warehouse.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class UnknownProductException extends RuntimeException {
    public UnknownProductException(String message) {
        super(message);
    }
}
