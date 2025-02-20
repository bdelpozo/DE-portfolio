# DE-Portfolio

This is my Data Engineer Portfolio. I'm going to share all I am currently learning from the Scala-Spark course I am
attending at the moment.

## Project Structure

The project is organized into several packages, each demonstrating a different concept:

- `BasicExamples`: Contains a first easy example to break the ice.
- `Config`: Contains a file with run configuration and ConfigParser with TypeSafe.config library.
- `DataStructures`: Contains an example of Data Structures.


## Dependencies

The project uses the following main dependencies:

- SBT version 1.10.0
- Scala version 2.13.14
- Apache Spark version 3.5.1
- ScalaTest version 3.2.18


## Building and Running

The project uses sbt for building and running. You can run the project using the following command:

```bash
sbt run
```

## Testing

Tests are be located in the src/test/scala directory. You can run the tests using the following command:

```bash
sbt test
```
