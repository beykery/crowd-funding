FROM livingobjects/jre8
MAINTAINER beykery "874343737@qq.com"
ENV TZ=Asia/Shanghai
ADD crowd-1.0.jar app.jar
RUN bash -c 'touch /app.jar'
RUN bash -c 'mkdir /uploadTmp'
EXPOSE 8080
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Dfile.encoding=UTF-8","-jar","/app.jar"]
