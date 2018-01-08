# SpringHelloWorld

My first simple SpringBoot Project 

## Stack

java8
spring-boot
swagger2
assertj
rest-assured
junit
hibernate
h2
gradle
elasticsearch
activemq

## Getting Started

1) Run Elasticsearch (for example using Docker):
```
docker pull elasticsearch:5.6.5
docker run -d -p 9200:9200 -p 9300:9300 <image_id>
```
2) Run ActiveMQ (for example using Docker):
```
docker pull webcenter/activemq
docker run -p 8161:8161 -p 61616:61616 <image_id>
```
3) Run SpringHelloWorld app (in main project directory)
```
gradlew clean bootRun
```

## TO-DO
* unit tests (current test coverage: 48%)
* docker-compose