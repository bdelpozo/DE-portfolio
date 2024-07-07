# Conduktor

Conduktor is a tool with an intuitive interface for managing Kafka clusters. It supports both local and cloud environments. It makes it easy to use for developers.

### Get Started
````bash
curl -L https://releases.conduktor.io/quick-start -o docker-compose.yml && docker compose up -d --wait && echo "Conduktor started on http://localhost:8080"
````
To download Conduktor:
https://www.conduktor.io/get-started/desktop/


### Structure
In it there are different tabs:
- `Overview`: provides an overview of the cluster's brokers, topics, consumers, etc.
- `Broker`: allows you to see more information about the brokers, the port they listen to, the partitions...
- `Topics`: allows you to explore the topics, their partitions and by clicking on a specific topic it is possible to see more detailed information.
- `Consumers`: lists the active consumers and their characteristics.
- `Schema Registry`: allows you to register new data schemas, for example in avro. Very useful for streaming data processing.
- `Kafka Connect`: 
- `Kafka Streams`:
- `ksqlDB`:


