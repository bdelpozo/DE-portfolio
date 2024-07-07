#!/bin/sh

# shellcheck disable=SC3046
. ./env.sh

echo Stoping Kafka
$dir_kafka/bin/kafka-server-stop.sh
# Sleep 5 seconds
sleep 5

echo Stoping Zookeeper
$dir_kafka/bin/zookeeper-server-stop.sh