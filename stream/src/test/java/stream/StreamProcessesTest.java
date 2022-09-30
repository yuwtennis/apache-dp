package stream;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StreamProcessesTest {

    private static final String srcTopic = "test-input" ;
    private static final String sinkTopic = "test-output" ;

    private static Properties loadProps() {
        Properties props = new Properties() ;
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());

        return props ;
    }

    @Test
    void TestWordCount() {
        StreamsBuilder builder = new StreamsBuilder();
        StreamProcesses.wordCount(builder, srcTopic, sinkTopic);
        Topology topology = builder.build();
        Properties props = loadProps() ;
        TopologyTestDriver testDriver = new TopologyTestDriver(topology, props);
        TestInputTopic<String, String> inputTopic = testDriver
                .createInputTopic(
                        srcTopic,
                        Serdes.String().serializer(),
                        Serdes.String().serializer());

        TestOutputTopic<String, Long> outputTopic = testDriver
                .createOutputTopic(
                        sinkTopic,
                        Serdes.String().deserializer(),
                        Serdes.Long().deserializer());

        inputTopic.pipeInput("", "Hello world.");

        assertEquals(new KeyValue<>(
                "Hello",
                1L), outputTopic.readKeyValue());

        assertEquals(new KeyValue<>(
                "world",
                1L), outputTopic.readKeyValue());

        testDriver.close();

    }
}
