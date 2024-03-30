package uz.ofs.constants;

import lombok.Getter;

@Getter
public enum ResponseCode {

    OK(200),
    BAD_REQUEST(400),
    NOT_FOUND(404),
    INTERNAL_SERVER_ERROR(500);

    private final Integer code;

    ResponseCode(int code) {
        this.code = code;
    }
}
