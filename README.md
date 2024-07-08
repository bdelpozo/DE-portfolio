# DE-Portfolio

This is my Data Engineer Portfolio. I'm going to share all I am currently learning from the Scala-Spark course I am
attending at the moment.

## Project Structure

The project is organized into several packages, each demonstrating a different concept:

- `basicExamples`: Contains a first easy example to break the ice.
- `config`: Contains a file with run configuration and ConfigParser with TypeSafe.config library.
- `dataStructures`: Contains an example of Data Structures.
- `spark`: Contains some examples of Spark applications and a SparkSessionWrapper very useful to use everywhere it's needed a Spar session.
- `implicits`: Contains an object with some useful classes to define the SparkSessionWrapper.

## Dependencies

The project uses the following main dependencies:

- SBT version 1.10.0
- Scala version 2.13.14
- Apache Spark version 3.5.1
- ScalaTest version 3.2.18
- MrPowers spark-fast-test version 1.3.0


## Building and Running

The project uses sbt for building and running. You can run the project using the following command:

```bash
sbt run
```

## Testing

Tests are located in the src/test/scala directory. You can run the tests using the following command:

```bash
sbt test
```
