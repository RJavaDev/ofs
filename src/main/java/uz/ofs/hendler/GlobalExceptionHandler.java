package uz.ofs.hendler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.webjars.NotFoundException;
import uz.ofs.constants.ResponseCode;
import uz.ofs.constants.ResponseMessage;
import uz.ofs.dto.dtoUtil.ApiResponse;
import uz.ofs.exception.FoodProductException;
import uz.ofs.exception.UserUnauthorized;
import uz.ofs.exception.UserUnauthorizedAction;
import uz.ofs.exception.UsernameNotFoundException;

import java.lang.reflect.InvocationTargetException;


@RestControllerAdvice
@Slf4j
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiResponse<Object> handleGlobalException(Exception ex) {
        return ApiResponse.build()
                .code(ResponseCode.INTERNAL_SERVER_ERROR)
                .body(ex.getMessage())
                .message(ResponseMessage.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(InvocationTargetException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiResponse<Object> handleInvocationTargetException(InvocationTargetException ex) {
        return ApiResponse.build()
                .code(ResponseCode.INTERNAL_SERVER_ERROR)
                .body(ex.getMessage())
                .message(ResponseMessage.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiResponse<Object> handleException(RuntimeException ex) {

        return ApiResponse.build()
                .code(ResponseCode.INTERNAL_SERVER_ERROR)
                .body(ex.getMessage())
                .message(ResponseMessage.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiResponse<Object> handleException(UsernameNotFoundException ex) {

        return ApiResponse.build()
                .code(ResponseCode.NOT_FOUND)
                .body(ex.getMessage())
                .message(ResponseMessage.NOT_FOUND);
    }

    @ExceptionHandler(FoodProductException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse<Object> handleException(FoodProductException ex) {

        return ApiResponse.build()
                .code(ResponseCode.BAD_REQUEST)
                .body(ex.getMessage())
                .message(ResponseMessage.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiResponse<Object> handleException(NotFoundException ex) {

        return ApiResponse.build()
                .code(ResponseCode.NOT_FOUND)
                .body(ex.getMessage())
                .message(ResponseMessage.NOT_FOUND);
    }

    @ExceptionHandler(UserUnauthorized.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse<Object> handleException(UserUnauthorized ex) {

        return ApiResponse.build()
                .code(ResponseCode.BAD_REQUEST)
                .body(ex.getMessage())
                .message(ResponseMessage.BAD_REQUEST);
    }

    @ExceptionHandler(UserUnauthorizedAction.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse<Object> handleException(UserUnauthorizedAction ex) {
        return ApiResponse.build()
                .code(ResponseCode.BAD_REQUEST)
                .body(ex.getMessage())
                .message(ResponseMessage.BAD_REQUEST);
    }
}
