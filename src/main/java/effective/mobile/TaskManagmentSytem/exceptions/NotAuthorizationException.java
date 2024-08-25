package effective.mobile.TaskManagmentSytem.exceptions;

import org.springframework.security.core.AuthenticationException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NotAuthorizationException extends RuntimeException {
    HttpServletRequest request;
    HttpServletResponse response;
    AuthenticationException e;

    public NotAuthorizationException(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) {
        super(e.getMessage());
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public AuthenticationException getE() {
        return e;
    }
}
