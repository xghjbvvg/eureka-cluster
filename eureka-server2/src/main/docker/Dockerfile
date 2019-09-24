FROM java:8
VOLUME /tmp
COPY eureka-server2-0.0.1-SNAPSHOT.jar eureka-server.jar
RUN bash -c "touch /eureka-server.jar"
EXPOSE 8762
ENTRYPOINT ["java","-jar","eureka-server.jar"]