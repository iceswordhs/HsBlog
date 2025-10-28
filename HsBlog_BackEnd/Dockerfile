FROM openjdk:8-jre-slim

WORKDIR /app

# 复制 JAR 包
COPY target/HsBlog_BackEnd-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 10000

# 使用CMD，可以被覆盖
CMD ["java", "-jar", "app.jar"]