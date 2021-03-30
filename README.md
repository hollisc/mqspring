# mqspring

Start the spring boot app:

```
./mvnw spring-boot:run -Dspring-boot.run.arguments="--QM=QM1 --CHANNEL=DEV.ADMIN.SVRCONN --CONNECTION_NAME=localhost(1414) --USER=admin --PASSWORD=passw0rd"
```

Pull the latest MQ docker image from docker hub:

``
docker pull ibmcom/mq:latest
``

Start the MQ docker image:

```
docker run --env LICENSE=accept --env MQ_QMGR_NAME=QM1 --volume qm1data:/mnt/mqm --publish 1414:1414 --publish 9443:9443 --detach --env MQ_APP_PASSWORD=passw0rd ibmcom/mq:latest
```

MQ Console:

```
https://localhost:9443/ibmmq/console
```
