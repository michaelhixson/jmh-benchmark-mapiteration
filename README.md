This is a JMH benchmark comparing techniques for iterating over map entries.

To build:

    mvn clean package

To run:

    java -jar target/benchmarks.jar

To run and profile the garbage collector:

    java -jar target/benchmarks.jar -prof gc
