Overview

## This is a multimodel gradel project for user service API using Async log4j2 with (ElasticSearch + Kibana + Filebeat) built over spring-boot boilerplate

### gradle tasks: ./gradlew or gradlew.bat 
* clean
* build
* buildDocker   
   * this will generate local image tagged hemeda2/basic-user-webservice:latest
* test
* jacocoTestReport
* gatlingRunStatflo  
    * Note : this should be run After`docker-compose up` and will target the user service container which will be running in port 8080

* find test reports in path `$ROOT Folder`
            `build/reports/tests//test/html/index.html`
            `build/reports/jacoco/test/html/index.html`
            `build/reports/findbugs/test/html/index.html`
* for Gatling Scala project, it will generate report in this path inside root folder `loadtest/build/gatling-results/***` 

### Root project user service
* Spring boot user service (
* Unit Test
* Docker File
* Docker Compose

### Sub project Scala Load test (loadtest folder under root folder)
* Load testing using Gatling framework for 2000 users over 140 seconds

### Technologies:-
* Docker gradle
* Filebeat configuration to upload spring logs from docker container to Elastic search using elastic REST API
* Log4j Async appender
* ElasticSearch + Kibana
* Mockito
* Flyway for datbase upgrade version management 
* Jackson JSON
* Google code style.xml for code styling and format

### Code Analysis and Test coverage tool:
* findbugs
* Jacoco

### Notes:
* this file "dos2unix.exe" used in case the docker-compose up faild to run/build user service with exception :"folder not found",
 in Windows OS. Since Windows OS editors(most of them) will auto/convert files ending to CRLF from LF, but it SHOULD be LF in our case, becuase we use *.sh files inside docker to download install filebeat inside docker container and deploy jar file.
 
* for more information in this issue please refer to
* https://help.github.com/articles/dealing-with-line-endings/
* https://forums.docker.com/t/standard-init-linux-go-17*exec-user-process-caused-no-such-file/20025
