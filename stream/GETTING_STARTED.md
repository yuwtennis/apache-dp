
## Quick start with word count

### Startup kafka

Follow instruction in official doc.

https://kafka.apache.org/quickstart

### Create topic

```markdown
bin/kafka-topics.sh --create --topic src-topic --bootstrap-server localhost:9092
bin/kafka-topics.sh --create --topic sink-topic --bootstrap-server localhost:9092
```

### Start producer

```markdown
bin/kafka-console-producer.sh --bootstrap-server localhost:9092 --topic src-topic
```

### Start consumer

```markdown
bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic sink-topic --value-deserializer "org.apache.kafka.common.serialization.LongDeserializer"
```

### Run app

```markdown
gradle run
```
