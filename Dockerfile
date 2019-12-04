FROM openjdk:9

WORKDIR /usr/src/myapp

CMD java -jar /usr/src/myapp/miniproject-1.0.jar

EXPOSE 80