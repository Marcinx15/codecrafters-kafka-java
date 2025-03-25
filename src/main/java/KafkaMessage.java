import java.nio.ByteBuffer;
import java.util.Arrays;

public class KafkaMessage {
    private final Integer messageSize;
    private final HeaderV2 header;

    public KafkaMessage(Integer messageSize, HeaderV2 header) {
        this.header = header;
        this.messageSize = messageSize;
    }

    public Integer getMessageSize() {
        return messageSize;
    }

    public HeaderV2 getHeader() {
        return header;
    }

    public byte[] toBytes() {
        ByteBuffer buffer = ByteBuffer.allocate(8);
        buffer.putInt(messageSize);
        buffer.putInt(header.correlationId());
        return buffer.array();
    }

    public static KafkaMessage fromBytes(int messageSize, byte[] bytes) {
        return new KafkaMessage(
                messageSize,
                HeaderV2.fromBytes(bytes)
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

