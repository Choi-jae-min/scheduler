package sparta.scheduler.dto;

public class DataResponseDto<D> extends ResponseDto<D> {
    public DataResponseDto(String message , D data) {
        super(message, data);
    }

    public static <D> DataResponseDto<D> of(String message,D data) {
        return new DataResponseDto<>(message, data);
    }
}
