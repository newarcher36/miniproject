FROM openjdk:9

WORKDIR /usr/src/myapp

COPY target/miniproject-1.0.jar .

CMD java -jar miniproject-1.0.jar

EXPOSE 80