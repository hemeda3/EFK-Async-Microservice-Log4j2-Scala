#!/bin/sh
# hack because elk was resolving to wrong IP of 198.105.244.228?
echo $(getent hosts elasticsearch) >> /etc/hosts
echo $(getent hosts elasticsearch)
until curl -s 'http://elasticsearch:9200' -o /dev/null; do
    sleep 30
done
while ! nc -z elasticsearch 9200; do sleep 5; done



filebeat -c /etc/filebeat/filebeat.yml -e &
java -Djava.security.egd=file:/dev/./urandom -jar /app.jar

#DONT FOGET TO UPDATE ANY EK