package az.azerconnect.azerconnectbackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class EmptyMsisdnListException extends RuntimeException {

    public EmptyMsisdnListException() {
        super();
    }

    public EmptyMsisdnListException(String message) {
        super(message);
    }


}
