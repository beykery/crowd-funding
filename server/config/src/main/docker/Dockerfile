FROM livingobjects/jre8
MAINTAINER beykery "874343737@qq.com"
ENV TZ=Asia/Shanghai
ADD config-1.0.jar app.jar
ADD config-repo config-repo
RUN bash -c 'touch /app.jar'
EXPOSE 8080
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
