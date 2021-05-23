package nl.janjongerden.warehouse.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class UnknownArticleException extends RuntimeException {
    public UnknownArticleException(String message) {
        super(message);
    }
}
