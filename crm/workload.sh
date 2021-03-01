#!/bin/bash


docker network create bookkeeping &
docker run -p 3306:3306 --name db --network bookkeeping -e MYSQL_DATABASE=fc_crm -e MYSQL_ROOT_PASSWORD=root -d mariadb && mvn clean install -f ~/playground/bookkeeping-crm/crm/pom.xml
docker build -t app ~/playground/bookkeeping-crm/crm && docker run --network bookkeeping --name bookkeeping-con -p 8080:8080 -d app
