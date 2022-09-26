# Overview

This repository sample configurations for stream related products.

# How to start stream

## Local

1. Download stream.

https://stream.apache.org/downloads

2. Start Zookeeper and Broker

https://stream.apache.org/quickstart

```
# Start Zookeeper
bin/zookeeper-server-start.sh config/zookeeper.properties

# Start Broker
bin/stream-server-start.sh config/server.properties

```

3. Start Kafka Connect as standalone mode

https://stream.apache.org/documentation/#connect_running

```
bin/connect-standalone.sh config/connect-standalone.properties $PROJDIR/connector/KAFKA_CONNCTOR_PROPERTY
```

## Docker
TBC

# Pre requisite

Configuration files are tested on below versions.

stream broker

| version | status |
| ------- | ------ |
| 2.4.1   | Tested |

stream connect

| version | status |
| ------- | ------ |
| 2.4.1   | Tested |

