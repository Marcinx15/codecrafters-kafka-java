import java.nio.ByteBuffer;

public record ApiVersionsResponseV4(
        Integer messageSize,
        HeaderV0 header,
        ErrorCode errorCode
) {

    public byte[] toBytes() {
        ByteBuffer buffer = ByteBuffer.allocate(10);
        buffer.putInt(messageSize);
        buffer.putInt(header.correlationId());
        buffer.putShort(errorCode.getValue());
        return buffer.array();
    }
}

enum ErrorCode {
    UNSUPPORTED_VERSION((short) 35);

    private final short value;

    ErrorCode(short value) {
        this.value = value;
    }

    public short getValue() {
        return value;
    }
}


