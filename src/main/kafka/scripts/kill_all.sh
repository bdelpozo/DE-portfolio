#!/usr/bin/bash
# Look for Kafka and Zookeeper processes and we kill them
# jps -l : lists java processes
# grep kafka : filters out java processes containing the word kafka
# awk '{print $1}' : prints the first column of the result set
# xargs kill -9 : kill the process

# ps -ef | grep kafka | grep -v grep | awk '{print $2}' | xargs kill -9
pgrep -f kafka | xargs kill -9
pgrep -f zookeeper | xargs kill -9

# Look for the process listening on port 9092 and kill it.
# netstat -tuln : lists the ports that are listening on port 9092.
# grep 9092 :  filters out the ports that contain port 9092
# awk '{print $7}' : prints the seventh column of the result set
# cut -d/ -f1 : prints the first part of the result set
# xargs kill -9 : kills the process
netstat -tuln | grep 9092 | awk '{print $7}' | cut -d/ -f1 | xargs kill -9
netstat -tuln | grep 9093 | awk '{print $7}' | cut -d/ -f1 | xargs kill -9
netstat -tuln | grep 9094 | awk '{print $7}' | cut -d/ -f1 | xargs kill -9
netstat -tuln | grep 2181 | awk '{print $7}' | cut -d/ -f1 | xargs kill -9