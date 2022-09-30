package stream;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Produced;

import java.util.Arrays;

// TODO Move to library
public class StreamProcesses {

    /**
     * Takes a stream of string lines and count words
     * @param builder
     * @return
     */
    public static void wordCount(final StreamsBuilder builder, String srcTopic, String sinkTopic) {
        KStream<String, String> source = builder.stream(
                srcTopic,
                Consumed.with(
                        Serdes.String(),
                        Serdes.String()));

        KTable<String, Long> counted = source
                .flatMapValues(value -> Arrays.asList(value.split("\\W+")))
                .groupBy((key, value) -> value)
                .count();

        counted.toStream().to(sinkTopic,
                Produced.with(
                        Serdes.String(),
                        Serdes.Long()));
    }
}
