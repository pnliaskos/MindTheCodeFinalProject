package gr.kariera.mindthecode.mindthecodefinalproject;

import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.*;
import java.util.stream.Collectors;

@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {
    private static final Logger LOG = LoggerFactory.getLogger(CustomGlobalExceptionHandler.class);

    @ExceptionHandler({ EntityNotFoundException.class })
    protected ResponseEntity<Object> handleNotFound(final RuntimeException ex, final WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", new Date());
        body.put("status", HttpStatus.NOT_FOUND);

        List<String> errors = new ArrayList<>(Arrays.asList(ex.getMessage(),  "Entity with id:"+ ex.getMessage() + " does not exist." ));

        body.put("errors", errors);
        return new ResponseEntity<>(body,  new HttpHeaders(), HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler({ Exception.class })
    public ResponseEntity<Object> handleAll(Exception ex, WebRequest request) {
        LOG.error(ex.getMessage());
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", new Date());
        body.put("status", HttpStatus.INTERNAL_SERVER_ERROR);

        List<String> errors = new ArrayList<>(Arrays.asList(ex.getMessage(),  "Unexpected error occured."));

        body.put("errors", errors);
        return new ResponseEntity<>(body,  new HttpHeaders(), HttpStatus.NOT_FOUND);

    }

}
