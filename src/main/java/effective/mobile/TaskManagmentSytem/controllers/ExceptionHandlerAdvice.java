package effective.mobile.TaskManagmentSytem.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import effective.mobile.TaskManagmentSytem.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(IllegalRequestArgumentException.class)
    public ResponseEntity<String> illJwtEx(IllegalRequestArgumentException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotAuthorizationException.class)
    public ResponseEntity<String> notAuthEx(NotAuthorizationException ex) throws IOException {
        ex.getResponse().setContentType(MediaType.APPLICATION_JSON_VALUE);
        ex.getResponse().setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        final Map<String, Object> body = new HashMap<>();
        body.put("status", HttpServletResponse.SC_UNAUTHORIZED);
        body.put("error", "Unauthorized");
        body.put("message", ex.getE().getMessage());
        body.put("path", ex.getRequest().getServletPath());

        final ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(ex.getResponse().getOutputStream(), body);

        return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(MalforemedJwtTokenException.class)
    public ResponseEntity<String> malformedJwtEx(MalforemedJwtTokenException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnknownServerException.class)
    public ResponseEntity<String> unknownExc(UnknownServerException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> userNotFoundExc(UserNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}
