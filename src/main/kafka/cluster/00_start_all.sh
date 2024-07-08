#!/bin/bash
echo Iniciando Zookeeper 1

# shellcheck disable=SC3046
source ./env.sh


rm -rf ./kafka-logs*
rm -rf ./zookeeper-data*
sleep 1

./01-2_start_zookeeper.sh

sleep 5

echo Iniciando Kafka
./02-2_start_kafka.sh