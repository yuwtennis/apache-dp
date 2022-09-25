package stream;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

public class PersonSerde {
    public static class JSONSerde<T extends JSONSerdeCompatible> implements Serializer<T>, Deserializer<T>, Serde<T> {
        @Override
        public void configure(final Map<String, ?> configs, final boolean isKey) {}

        @Override
        public T deserialize(final String topic, final byte[] data) {
            // TODO write deserialize process

        }

        @Override
        public byte[] serialize(final String topic, final T data) {
            // TODO write serialize process
        }

        @Override
        public void close() {}

        @Override
        public Serializer<T> serializer() {
            return this;
        }

        @Override
        public Deserializer<T> deserializer() {
            return this;
        }

    }

    // JacksonProperty
    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "_t")
    @JsonSubTypes({
        @JsonSubTypes.Type(value = Person.class, name = "Person")
    })

    interface JSONSerdeCompatible { }

    static public class Person implements JSONSerdeCompatible {
        public String firstName;
        public String lastName;
    }
}
