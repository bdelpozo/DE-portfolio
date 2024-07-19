#!/bin/bash

# Script to start Zookeeper. Zookeeper start script is executed with its properties.

. ./env.sh

echo Starting Zookeeper
$dir_kafka/bin/zookeeper-server-start.sh $dir_kafka/config/zookeper.properties &