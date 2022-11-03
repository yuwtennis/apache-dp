package app;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.*;

import java.util.Arrays;

/**
 * Class with examples of Stream processing
 */
public class StreamProcesses {

    /**
     * Takes a stream of string lines and count words then send it to another topic
     * @param builder
     * @param srcTopic
     * @param sinkTopic
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

    /**
     * Takes a stream of Person POJO and proxy it to another topic
     * @param builder
     * @param srcTopic
     * @param sinkTopic
     */
    public static void simpleObjectTransmission(final StreamsBuilder builder, String srcTopic, String sinkTopic) {
        KStream<String, PersonSerde.Person> source = builder.stream(
                srcTopic,
                Consumed.with(
                        Serdes.String(),
                        new PersonSerde.JSONSerde<>()
                        )
        );

        source.mapValues(new ValueMapper<PersonSerde.Person, PersonSerde.Person>() {
            @Override
            public PersonSerde.Person apply(PersonSerde.Person person) {
                System.out.println(person.firstName);
                System.out.println(person.lastName);
                System.out.println(person.timestamp);

                return person ;
            }
        });

        source.to(sinkTopic,
                Produced.with(
                        Serdes.String(),
                        new PersonSerde.JSONSerde<>()));
     }
}
