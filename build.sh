#!/bin/bash

PREFIX=/etc/webapp

cd $PREFIX
git pull

rm webapp.war
mvn clean package
mv target/ito-1.0.war webapp.war
