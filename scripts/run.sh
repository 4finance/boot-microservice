#!/bin/bash

### Configuration which should be placed on server where app is deployed
# Environment where our app is deployed, configuration will be taken from corresponding dir
export APP_ENV="prod"
# Git repository with configuration. Absolute path should be used.
export CONFIG_FOLDER="properties"
# For secretProd env, ENCRYPT_KEY is required.
# Use encrypt.key if you pass it as -D option when running java
export ENCRYPT_KEY="secretEncryptKey"
###

pushd ..
# For production mode (no stubs) use prod
# For developer mode (stubs and embedded Zookeeper) use env
./gradlew $@ build
java -Dspring.profiles.active=dev -jar ./build/libs/boot-microservice-CD-000.jar
popd
