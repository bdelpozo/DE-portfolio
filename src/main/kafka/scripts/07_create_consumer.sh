#!/bin/bash

# Script to create a consumer for a topic

source env.sh

echo Creating topic
#./04_create_topic.sh
topicname="topic-1"
# if we run 04_create_topic.sh please comment topicname or it will be overwritten
# if not, we can write topic name in the variable and execute the script as it is now

# We create a consumer for the topic: The command kafka-console-consumer.sh allows us to consume messages from a topic.
position=--from-beginning
# position=--offset 0
# position=--partition 0
# position=--partition 1
# position="--offset earliest"
#$dir_kafka/bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic $topicname --from-beginning
$dir_kafka/bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic $topicname $position --formatter kafka.tools.DefaultMessageFormatter --property print.timestamp=true --property print.key=true --property key.deserializer=org.apache.kafka.common.serialization.StringDeserializer --property value.deserializer=org.apache.kafka.common.serialization.StringDeserializer


# options
# --bootstrap-server: kafka server
# --topic: Topic name
# --partition: Partition number

# --from-beginning: Read messages from the beginning
# --max-messages: Reads a maximum number of messages
# --earliest: Read messages from the beginning
# --offset: Read messages from an offset
# --property print.key=true: Print the message key
# --max-messages: Reads maximum number of messages
# --timeout-ms: Timeout for message reading
# --key-deserializer
# --key-deserializer: Key serializer
# --value-deserializer: Value serializer
# Possible values are:
# org.apache.kafka.common.serialization.StringDeserializer
# org.apache.kafka.common.serialization.ByteArrayDeserializer
# org.apache.kafka.common.serialization.IntegerDeserializer
# org.apache.kafka.common.serialization.LongDeserializer
# org.apache.kafka.common.serialization.DoubleDeserializer
# org.apache.kafka.common.serialization.FloatDeserializer
# org.apache.kafka.common.serialization.ShortDeserializer
# org.apache.kafka.common.serialization.ByteDeserializer
# org.apache.kafka.common.serialization.BytesDeserializer
# org.apache.kafka.common.serialization.BooleanDeserializer
# org.apache.kafka.common.serialization.ByteBufferDeserializer
# org.apache.kafka.common.serialization.UUIDDeserializer
# org.apache.kafka.common.serialization.DateDeserializer
# org.apache.kafka.common.serialization.TimeDeserializer
# org.apache.kafka.common.serialization.TimestampDeserializer
# org.apache.kafka.common.serialization.InstantDeserializer
# org.apache.kafka.common.serialization.LocalDateDeserializer
# org.apache.kafka.common.serialization.LocalDateTimeDeserializer
# org.apache.kafka.common.serialization.LocalTimeDeserializer
# org.apache.kafka.common.serialization.ZonedDateTimeDeserializer
# org.apache.kafka.common.serialization.DurationDeserializer
# org.apache.kafka.common.serialization.MonthDayDeserializer
# org.apache.kafka.common.serialization.YearDeserializer

## Message formatting
#--formatter : The name of the message formatting class to display. (default: kafka.tools.DefaultMessageFormatter)
# --formatter-config : Properties for the message formatter. (default: none)
# <String: config Config properties file to initialize
# --property print.timestamp=true: Print the timestamp of the message.

# Formatting examples
#$dir_kafka/bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic topicname --from-beginning --formatter kafka.tools.DefaultMessageFormatter --property print.timestamp=true --property print.key=true --property key.deserializer=org.apache.kafka.common.serialization.StringDeserializer --property value.deserializer=org.apache.kafka.common.serialization.StringDeserializer

