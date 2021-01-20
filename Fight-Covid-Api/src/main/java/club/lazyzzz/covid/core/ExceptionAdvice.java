package club.lazyzzz.covid.core;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice("club.lazyzzz.covid.controller")
public class ExceptionAdvice {

    @ExceptionHandler(RuntimeException.class)
    public Response handleRuntimeException(RuntimeException e) {
        return buildResponse(e);
    }

    private Response buildResponse(RuntimeException e) {
        Response response = new Response();
        response.setMsg(e.getMessage());
        response.setCode(HttpStatus.BAD_REQUEST.value());
        return response;
    }
}
