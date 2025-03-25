import java.nio.ByteBuffer;

public class KafkaMessage {
    private final Integer messageSize;
    private final HeaderV0 header;

    public KafkaMessage(HeaderV0 header) {
        this.header = header;
        this.messageSize = 0;
    }

    public byte[] toBytes() {
        ByteBuffer buffer = ByteBuffer.allocate(8);
        buffer.putInt(messageSize);
        buffer.putInt(header.correlationId());
        return buffer.array();
    }
}

