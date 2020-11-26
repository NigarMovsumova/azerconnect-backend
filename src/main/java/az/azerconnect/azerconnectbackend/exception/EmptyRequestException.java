package az.azerconnect.azerconnectbackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class EmptyRequestException extends RuntimeException {

    public EmptyRequestException() {
        super();
    }

    public EmptyRequestException(String message) {
        super(message);
    }


}