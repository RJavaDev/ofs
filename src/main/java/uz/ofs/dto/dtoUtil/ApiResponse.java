package uz.ofs.dto.dtoUtil;

import lombok.Getter;
import uz.ofs.constants.ResponseCode;

@Getter
public class ApiResponse<T>{

    private String message;

    private T body;

    private int code;

    public ApiResponse<T> message(String message){
        this.message = message;
        return this;
    }

    public ApiResponse<T> body(T body){
        this.body=body;
        return this;
    }

    public ApiResponse<T> code(ResponseCode code){
        this.code = code.getCode();
        return this;
    }

    public static <T> ApiResponse<T> build() {
        return new ApiResponse<>();
    }
}
