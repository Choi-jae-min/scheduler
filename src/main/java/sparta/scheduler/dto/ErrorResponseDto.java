package sparta.scheduler.dto;

public class ErrorResponseDto<D> extends ResponseDto<D> {
    public ErrorResponseDto(String message , D data) {
        super(message , data);
    }

    public ErrorResponseDto(String message) {
        super(message , null);
    }

    public static <D> ErrorResponseDto<D> withData(String message , D data) {
        return new ErrorResponseDto<>(message ,data);
    }

    public static <D> ErrorResponseDto<D> of(String message) {
        return new ErrorResponseDto<>(message);
    }
}
