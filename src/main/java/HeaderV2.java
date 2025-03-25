import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public record HeaderV2(
        Short requestApiKey,
        Short requestApiVersion,
        Integer correlationId,
        String clientId,
        List<String> tagBuffer
) {
    public static HeaderV2 fromBytes(byte[] bytes) {
        ByteBuffer buffer = ByteBuffer.wrap(bytes);
        return new HeaderV2(
                buffer.getShort(),
                buffer.getShort(),
                buffer.getInt(),
                "random",
                new ArrayList<>()
        );
    }
}
