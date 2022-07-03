#!/bin/bash
echo "building rabbit producer"
cd ./demo-rabbit-producer
chmod +x ./gradlew
./gradlew clean build -x test

echo "building application reader"
cd ./demo-rabbit-consumer
chmod +x ./gradlew
./gradlew clean build -x test
