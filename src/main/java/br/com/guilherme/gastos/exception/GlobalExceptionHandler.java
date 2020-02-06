package br.com.guilherme.gastos.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler  {

    private ResponseEntity<ApiError> buildResponseEntity(ApiError apiError) {
        return ResponseEntity.status(HttpStatus.valueOf(apiError.getStatus())).body(apiError);
    }

    private String getRequestUri(HttpServletRequest request) {
        String uri = (String) request.getAttribute(WebUtils.INCLUDE_REQUEST_URI_ATTRIBUTE);
        if (uri == null) {
            uri = request.getRequestURI();
        }
        return uri;
    }

    @ExceptionHandler(value = {ServiceException.class})
    public ResponseEntity<ApiError> handleServiceException(ServiceException exception, HttpServletRequest request) {
        return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, exception.getMessage(), getRequestUri(request)));
    }

    @ExceptionHandler(value = {ServiceRuntimeException.class})
    public ResponseEntity<ApiError> handleServiceRuntimeException(ServiceRuntimeException exception, HttpServletRequest request) {
        return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, exception.getMessage(), getRequestUri(request)));
    }

    @ExceptionHandler(value = {BadCredentialsException.class})
    public ResponseEntity<ApiError> handleBadCredentaislException(HttpServletRequest request) {
        return buildResponseEntity(new ApiError(HttpStatus.UNAUTHORIZED, "Usuário ou senha inválidos",
                getRequestUri(request)));
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public final ResponseEntity<ApiError> handleNotValidArguments(MethodArgumentNotValidException exception,
                                                                  HttpServletRequest request) {

        final ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, "Campos inválidos no request",
                getRequestUri(request));

        exception.getBindingResult().getFieldErrors().forEach(
                fieldError -> apiError.addFieldError(fieldError.getObjectName(), fieldError.getField(),
                        fieldError.getDefaultMessage())
        );

        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ApiError> handleAllExceptions(Exception ex) {
        ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        return buildResponseEntity(apiError);
    }

}
