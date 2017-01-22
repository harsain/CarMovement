# Car Movement

Used Command Design pattern for this Car Movement App, which is a behavioral pattern. It's used to manage algorithms, relationships and responsibilities between objects.

## Documentation

The documentation is under doc directory

## Commands:

- INIT
  (requires 3 arguments- Int x, Int y, String direction) 
- GPS_REPORT
- TURN_LEFT
- TURN_RIGHT
- FORWARD
- QUIT

The first command has to be INIT / QUIT
command cases are ignored so both uppercase and lowercase can be used

## Tests

Basic tests have been written.
Run tests:
```
$ mvn clean

$ mvn test
```

## Build
```
$ mvn clean build
```

## Package
```
$ mvn clean package
```
Package will create a jar file which we can use to run the App

## Run
Make sure we have executed the package command, to create the jar file.

```
$ java -cp target/CarMovement-1.0-SNAPSHOT.jar com.harsain.App
```