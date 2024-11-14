# 使用 OpenJDK 作为基础镜像
FROM openjdk:17 AS build-stage

# 设置工作目录
WORKDIR /app

# 复制 pom.xml 和源代码文件到容器
COPY pom.xml .
COPY src ./src

# 构建 Spring Boot 应用
RUN ./mvnw clean package -DskipTests

# 使用较小的 JRE 镜像作为最终镜像
FROM openjdk:17-jre-slim

# 设置工作目录
WORKDIR /app

# 将构建好的 jar 文件从 build-stage 复制到当前镜像中
COPY --from=build-stage /app/target/opera-springboot.jar /app/opera-springboot.jar

# 设置容器启动时执行的命令
CMD ["java", "-jar", "opera-springboot.jar"]

# 暴露端口
EXPOSE 8080
