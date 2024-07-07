#!/bin/bash

source env.sh

topicname="topic-1"

echo Deleting topic $topicname

$dir_kafka/bin/kafka-topics.sh --bootstrap-server localhost:9092 --delete --topic $topicname