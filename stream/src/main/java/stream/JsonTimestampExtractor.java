package stream;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.streams.processor.TimestampExtractor;

public class JsonTimestampExtractor implements TimestampExtractor {
    /**
     *
     * @param record
     * @param partitionTime
     * @return
     */
    @Override
    public long extract(final ConsumerRecord<Object, Object> record, final long partitionTime) {
        if (record.value() instanceof PersonSerde.Person) {
            return ((PersonSerde.Person) record.value()).timestamp ;
        }
        throw new IllegalArgumentException();
    }
}
