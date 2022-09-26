package stream;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

public class PersonSerde {
    public static class JSONSerde<T extends JSONSerdeCompatible> implements Serializer<T>, Deserializer<T>, Serde<T> {
        private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper() ;

        @Override
        public void configure(final Map<String, ?> configs, final boolean isKey) {}

        /**
         * Convert byte[] into user defined Java Object
         * @param topic Name of source topic
         * @param data Data consumed from topic
         * @return Deserialized Java object
         */
        @SuppressWarnings("unchecked")
        @Override
        public T deserialize(final String topic, final byte[] data) {
            if (data == null) {
                return null;
            }

            try {
                return (T) OBJECT_MAPPER.readValue(data, JSONSerdeCompatible.class);
            } catch(final Exception e) {
                throw new SerializationException(e);
            }
        }

        /**
         * Convert Java Object into byte array for transmission
         * @param topic Name of sink topic
         * @param data Data to produce to topic
         * @return Serialized byte array
         */
        @Override
        public byte[] serialize(final String topic, final T data) {
            if (data == null) {
                return null;
            }

            try {
                return OBJECT_MAPPER.writeValueAsBytes(data);
            } catch(final Exception e) {
                throw new SerializationException(e);
            }
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
        public Long timestamp;

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }
    }
}
