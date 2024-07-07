#!/bin/bash

# Script to start Kafka. Kafka start script is executed with its properties.

. ./env.sh

echo Starting Kafka
$dir_kafka/bin/kafka-server-start.sh $dir_kafka/config/server.properties &