#!/bin/bash

# Script to start Zookeeper and then Kafka. It's also included rm propmts to remove data directories

# shellcheck disable=SC3046
source env.sh

rm -rf kafka-logs-*
rm -rf zookeeper.data*

echo Starting Zookeeper
./00_start_zookeeper.sh
sleep 5
echo Starting Kafka
./01_start_kafka.sh