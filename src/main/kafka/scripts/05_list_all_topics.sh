#!/bin/bash

# Script to list topics

source env.sh

echo Listing Topics

$dir_kafka/bin/kafka-topics.sh --bootstrap-server localhost:9092 --list