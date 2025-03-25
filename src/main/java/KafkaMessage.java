import java.nio.ByteBuffer;
import java.util.Arrays;

public class KafkaMessage {
    private final Integer messageSize;
    private final HeaderV2 header;

    public KafkaMessage(Integer messageSize, HeaderV2 header) {
        this.header = header;
        this.messageSize = messageSize;
    }

    public byte[] toBytes() {
        ByteBuffer buffer = ByteBuffer.allocate(8);
        buffer.putInt(messageSize);
        buffer.putInt(header.correlationId());
        return buffer.array();
    }

    public static KafkaMessage fromBytes(byte[] bytes) {
        ByteBuffer buffer = ByteBuffer.wrap(bytes);
        return new KafkaMessage(
                buffer.getInt(),
                HeaderV2.fromBytes(Arrays.copyOfRange(bytes, 4, bytes.length))
        );
    }

    @Override
    public String toString() {
        return "KafkaMessage{" +
                "messageSize=" + messageSize +
                ", header=" + header +
                '}';
    }
}

