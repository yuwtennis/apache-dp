
# Overview

This repository sample configurations for kafka related products.

# How to start kafka

## Local

1. Download kafka.

https://kafka.apache.org/downloads

2. Start Zookeeper and Broker

https://kafka.apache.org/quickstart

```
# Start Zookeeper
bin/zookeeper-server-start.sh config/zookeeper.properties

# Start Broker
bin/kafka-server-start.sh config/server.properties

```

3. Start Kafka Connect as standalone mode

https://kafka.apache.org/documentation/#connect_running

```
bin/connect-standalone.sh config/connect-standalone.properties $PROJDIR/connector/KAFKA_CONNCTOR_PROPERTY
```

## Docker
TBC

# Pre requisite

Configuration files are tested on below versions.

kafka broker

| version | status |
| ------- | ------ |
| 2.4.1   | Tested |

kafka connect

| version | status |
| ------- | ------ |
| 2.4.1   | Tested |

