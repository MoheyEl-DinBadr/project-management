FROM ubuntu:latest

MAINTAINER Mohey El-Din Badr "MoheyElDin.Badr@gmial.com"

RUN apt-get update && apt-get install -y openjdk-8-jdk
#jdbc:driver://hostname:port/dbname
ENV jdbcurl "jdbc:postgresql://pmadatabaseaws.ccsuanbulblq.us-east-2.rds.amazonaws.com:5432/postgres"
ENV dbuser "postgres"
ENV dbpass "password321"
ENV version "aws-db-usage"

WORKDIR /usr/local/bin

ADD  target/pma-app.jar .

ENTRYPOINT ["java","-jar", "pma-app.jar"]