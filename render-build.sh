#!/usr/bin/env bash

# Set JAVA_HOME
export JAVA_HOME=/opt/render/project/java

# Build the project using Maven
./mvnw clean install
