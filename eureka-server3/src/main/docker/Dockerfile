FROM java:8
VOLUME /tmp
COPY eureka-server3-0.0.1-SNAPSHOT.jar eureka-server.jar
RUN bash -c "touch /eureka-server.jar"
EXPOSE 8761
ENTRYPOINT ["java","-jar","eureka-server.jar"]