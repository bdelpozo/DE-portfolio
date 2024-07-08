#!/bin/bash

# Script to stop Kafka and then Zookeeper.

# shellcheck disable=SC3046
source env.sh

echo Stoping Kafka
$dir_kafka/bin/kafka-server-stop.sh
echo Stoping Zookeeper
$dir_kafka/bin/zookeeper-server-stop.sh