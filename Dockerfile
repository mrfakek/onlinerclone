FROM maven:3-eclipse-temurin-17 as build
RUN mkdir -p /build
WORKDIR /build
COPY pom.xml /build
RUN mvn -B dependency:resolve dependency:resolve-plugins
COPY src /build/src
RUN mvn package

FROM tomcat:10-jdk17-temurin as integrate
COPY --from=build /build/target/ROOT.war /usr/local/tomcat/webapps/