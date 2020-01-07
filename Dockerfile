FROM openjdk:11
#COPY --from=builder /usr/src/app/target/miniproject-1.0.git adjar /usr/app/miniproject-1.0.jar
COPY target/miniproject-1.0.jar /usr/app/miniproject-1.0.jar
ENTRYPOINT ["java","-jar","/usr/app/miniproject-1.0.jar"]