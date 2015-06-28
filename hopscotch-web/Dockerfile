#
# Can be build by docker using the command:
#  docker build -t charliek/hopscotch-web .
#
# Can be run by docker using the command:
#  docker run -p 5050:5050 -it charliek/hopscotch-web
#

FROM java:openjdk-8-jre
MAINTAINER charlie.knudsen@gmail.com

EXPOSE 5050
ENV JAVA_OPTS -server -Djava.awt.headless=true -Xms256M -Xmx1024M -Dsun.net.inetaddr.ttl=60 -Dlogback.statusListenerClass="ch.qos.logback.core.status.OnConsoleStatusListener" -Dlogback.configurationFile="/logback.xml"
ENTRYPOINT java $JAVA_OPTS -jar /app.jar

ADD build/libs/hopscotch-web-0.0.1-SNAPSHOT-all.jar /app.jar
ADD src/main/resources/docker-logback.xml /logback.xml
