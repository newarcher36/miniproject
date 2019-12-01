FROM openjdk:9

WORKDIR /usr/src/myapp

CMD java -jar target/miniproject-1.0.jar

EXPOSE 80