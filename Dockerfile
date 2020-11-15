FROM openjdk:14
EXPOSE 5005
EXPOSE 5037
COPY . /tmp
WORKDIR /tmp
CMD ["java", "-jar", "balancer_spring-0.0.1.jar"]
