#!/bin/sh
echo Iniciando Kafka

. ./env.sh

echo Starting Broker 1
$dir_kafka/bin/kafka-server-start.sh $dir_kafka/config/server.properties &

echo Starting Broker 2
$dir_kafka/bin/kafka-server-start.sh $dir_kafka/config/server2.properties &


echo Starting Broker 3
$dir_kafka/bin/kafka-server-start.sh $dir_kafka/config/server3.properties &