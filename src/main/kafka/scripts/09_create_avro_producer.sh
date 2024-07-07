#!/bin/sh
. ./env.sh


# Number of messages to send
max_messages=5000
# Time to wait between messages
sleep_time=0
batch_size=5000
# Topic that will receive messages
topicname="topic-1"

# Crear un productor de mensajes en formato AVRO
# Extracted Kafka producer command as constant.
${dir_kafka}bin/kafka-avro-console-producer --broker-list localhost:9092 --topic $topicname --property \"parse.key=true\" --property \"key.separator=:\" --property \"value.schema='{\"type\":\"record\",\"name\":\"myrecord\",\"fields\":[{\"name\":\"f1\",\"type\":\"string\"}]}'\"