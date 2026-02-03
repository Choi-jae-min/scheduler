package sparta.scheduler.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class InvalidPasswordException extends RuntimeException {
    public InvalidPasswordException() {
        super("잘못된 비밀번호입니다.");
    }
}
