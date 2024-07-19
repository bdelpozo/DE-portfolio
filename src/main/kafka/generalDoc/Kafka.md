# Kafka directories

- `bin`: Contains the Kafka scripts.
    - `kafka-topics.sh`: Script to create, list and describe topics.
    - `kafka-console-producer.sh`: Script to create a producer in console mode.
    - `kafka-console-consumer.sh`: Script to create a consumer in console mode.
    - `kafka-server-start.sh`: Script to start the Kafka server.
    - `kafka-server-stop.sh`: Script to stop the Kafka server.
    - `kafka-run-class.sh`: Script to run a Java class.
    - `kafka-configs.sh`: Script to modify the Kafka configuration.
    - `kafka-acls.sh`: Script to manage access control lists.
    - `kafka-broker-api-versions.sh`: Script to display broker API versions.
    - `kafka-consumer-groups.sh`: Script to manage consumer groups.
    - `kafka-consumer-offset-checker.sh`: Script to check consumer offsets.
    - `kafka-consumer-perf-test.sh`: Script to perform consumer performance tests.
    - `kafka-log-dirs.sh`: Script to display Kafka's log directories.
    - `kafka-mirror-maker.sh`: Script to create a Kafka mirror.
- `config`: Contains the Kafka configuration files.
    - `zookeeper.properties`: Zookeeper configuration file.
    - `server.properties`: Kafka server configuration file.
    - `producer.properties`: Kafka producer configuration file.
    - `consumer.properties`: Kafka consumer configuration file.
    - `connect-distributed.properties`: Kafka Connect configuration file.
    - `connect-standalone.properties`: Kafka Connect standalone configuration file.
    - `connect-log4j.properties`: Log4j configuration file for Kafka Connect.
    - `connect-runtime.properties`: Kafka Connect runtime configuration file.mer-perf-test.sh`: Script to perform consumer performance tests.
- `libs`: Contains the Kafka libraries.
- `logs`: Contains the Kafka logs.
- `site-docs`: Contains the Kafka documentation.
- `zookeeper`: Contains the Zookeeper files.
- `zookeeper-data`: Contains Zookeeper data.
- `zookeeper-logs`: Contains Zookeeper logs.

## Kafka's administration

## Introduction

Apache Kafka is a distributed broadcast platform used to publish and subscribe to log streams, similar to a message queue or messaging system. It is fast, scalable and durable.

## Installation

### Download

```shell
wget https://downloads.apache.org/kafka/2.8.0/kafka_2.13-3.7.0.tgz
```

### Decompression
```shell
tar -xvzf kafka_2.13-3.7.0.tgz
```
### Start

```shell
cd kafka_2.13-3.7.0
```
#### Zookeeper
We will start the Zookeeper server, as Kafka depends on it.
```shell
bin/zookeeper-server-start.sh config/zookeeper.properties
```
#### Kafka broker
We will start Kafka server.

```shell
bin/kafka-server-start.sh config/server.properties
```

## Comands

### Create a topic

```shell
bin/kafka-topics.sh --create --topic topic-1 --bootstrap-server localhost:9092 --partitions 5 --replication-factor 1
```

### List topics

```shell
bin/kafka-topics.sh --list --bootstrap-server localhost:9092
```

### Describe a topic

```shell
bin/kafka-topics.sh --describe --topic topic-1 --bootstrap-server localhost:9092
```

### Creation of messages

```shell
bin/kafka-console-producer.sh --topic topic-1 --bootstrap-server localhost:9092
```
After executing the above command, a terminal will open in which we can write the messages we want to send to the `topic-1` topic.

If we want to send a message with a password, we can do it in the following way:

```shell
bin/kafka-console-producer.sh --topic topics1 --bootstrap-server localhost:9092 --property "parse.key=true" --property "key.separator=:"
```
After executing the above command, a terminal will open in which we can write the messages we want to send to the `topic-1` topic. In this case, the messages must have a key and a value separated by `:`.

```shell
key1:value1
key2:value2
```

### Consume messages

```shell
bin/kafka-console-consumer.sh --topic topic-1 --from-beginning --bootstrap-server localhost:9092
```

### Delete a topic

```shell
bin/kafka-topics.sh --delete --topic topic-1 --bootstrap-server localhost:9092
```

## Group of consumers

Un grupo de consumidores es un conjunto de consumidores que trabajan juntos para consumir mensajes de uno o más tópicos. Cada grupo de consumidores tiene un identificador único y cada consumidor dentro del grupo tiene un identificador único.

### Creation of a group of consumers

The creation of a consumer group is done automatically when a consumer subscribes to a topic for the first time.

```shell
bin/kafka-consumer-groups.sh --bootstrap-server localhost:9092 --create --group group1
```

### List a group of consumers

```shell
bin/kafka-consumer-groups.sh --list --bootstrap-server localhost:9092
```

### Describe a group of consumers

```shell
bin/kafka-consumer-groups.sh --describe --group group1 --bootstrap-server localhost:9092
```

### Delete a group of consumers

```shell
bin/kafka-consumer-groups.sh --delete --group group1 --bootstrap-server localhost:9092
```
