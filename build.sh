#!/bin/bash

rm webapp.war
mvn clean package
mv target/ito-1.0.war webapp.war
