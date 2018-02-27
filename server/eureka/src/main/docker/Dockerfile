FROM livingobjects/jre8
#FROM java:8
MAINTAINER beykery "874343737@qq.com"
ENV TZ=Asia/Shanghai
ADD eureka-1.0.jar app.jar
RUN bash -c 'touch /app.jar'
EXPOSE 8080
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
