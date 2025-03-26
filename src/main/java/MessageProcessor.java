public class MessageProcessor {

    public ApiVersionsResponseV4 process(KafkaMessage message) {
        return new ApiVersionsResponseV4(
                0,
                new HeaderV0(message.getHeader().correlationId()),
                ErrorCode.UNSUPPORTED_VERSION
        );
    }
}
