package uz.ofs.exception;

public class AuthenticationException extends RuntimeException {
    public AuthenticationException(String massage) {
        super(massage);
    }
}

