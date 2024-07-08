#!/bin/bash

# Script to create a topic.

source env.sh

# Set here the specific data for the topic we are going to create
topicname="topic-1"
numpartitions=10
numreplication=1

echo Creating $topicname with $numpartitions partitions

$dir_kafka/bin/kafka-topics.sh --bootstrap-server localhost:9092 --create --topic $topicname --partitions $numpartitions --replication-factor $numreplication

#$dir_kafka/bin/zookeeper-shell.sh localhost:2181
#$dir_kafka/bin/zookeeper-shell.sh localhost:2181/brokers/topics/topics1/partitions
#$dir_kafka/bin/zookeeper-shell.sh localhost:2181/brokers/topics/topics1/partitions/partition_0/state

#ls /brokers/topics/
#
#ls /brokers/topics/topics1/partitions/partition_0/state