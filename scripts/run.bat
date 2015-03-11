rem ### Configuration which should be placed on server where app is deployed
rem Environment where our app is deployed, configuration will be taken from corresponding dir
@set APP_ENV="prod"
rem Git repository with configuration. Absolute path should be used.
@set CONFIG_FOLDER="properties"
rem For secretProd env, ENCRYPT_KEY is required.
rem Use encrypt.key if you pass it as -D option when running java
@set ENCRYPT_KEY="secretEncryptKey"
rem ###

pushd ..
rem For production mode (no stubs) use prod
rem For developer mode (stubs and embedded Zookeeper) use env
gradlew.bat bootRun %* -Dspring.profiles.active=dev
popd
