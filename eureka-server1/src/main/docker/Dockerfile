FROM java:8
VOLUME /tmp
COPY eureka-server1-0.0.1-SNAPSHOT.jar eureka-server.jar
RUN bash -c "touch /eureka-server.jar"
EXPOSE 8763
ENTRYPOINT ["java","-jar","eureka-server.jar"]