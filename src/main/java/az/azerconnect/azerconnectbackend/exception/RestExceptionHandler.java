package az.azerconnect.azerconnectbackend.exception;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({EmptyMsisdnListException.class})
    protected ResponseEntity<Object> handleEmptyMsisdnListException(EmptyMsisdnListException ex) {
        return new ResponseEntity<>(new GeneralException(ex.getMessage(), "404"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({EmptyRequestException.class})
    protected ResponseEntity<Object> handleEmptyRequestException(EmptyRequestException ex) {
        return new ResponseEntity<>(new GeneralException(ex.getMessage(), "404"), HttpStatus.NOT_FOUND);
    }

}