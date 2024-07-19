#!/usr/bin/bash

# Script to set dir_kafka variable to the directory Kafka is that it's used very frequently

export dir_kafka=$(ls -d kafka_2.13*/)
echo "env.sh dir_kafka=$dir_kafka"