#Overview
##This is multimodel gradel project user service API using Async log4j2 with (ElasticSearch + Kibana + Filebeat), includes:

### gradle tasks:
1- clean
2- build
3- buildDocker
4- test
5- jacocoTestReport
6- gatlingRunStatflo


 ###  A- Root project user service
1- Spring boot user service (
2- Unit Test
3- Docker File
4- Docker Compose

  ### B- Sub project Scala Load test (loadtest folder under root folder)
*Load testing using Gatling framework for 2000 users over 140 seconds

## Technologies:-
1- Docker gradle
2- Filebeat configuration to upload spring logs from docker container to Elastic search using elastic REST API
3- Log4j Async appender
4- ElasticSearch + Kibana
5- Mockito

## Code Anaylisis and coverage tool:
1- findbugs
2- Jacoco

## Notes:
* this file "dos2unix.exe" used in case the docker-compose up faild to run/build user service with exception :"folder not found",
 in Windows OS.Since Windows OS editors(most of them) will auto/convert files ending to CRLF from LF, but it SHOULD be LF
* for more information in this issue please refer to
* 1-https://help.github.com/articles/dealing-with-line-endings/
* 2-https://forums.docker.com/t/standard-init-linux-go-175-exec-user-process-caused-no-such-file/20025