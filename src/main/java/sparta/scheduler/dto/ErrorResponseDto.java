package sparta.scheduler.dto;

public class ErrorResponseDto<D> extends ResponseDto<D> {
    public ErrorResponseDto(String message) {
        super(message , null);
    }

    public static <D> ErrorResponseDto<D> of(String message) {
        return new ErrorResponseDto<>(message);
    }
}
