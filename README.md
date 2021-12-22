# evidence-computer
![build](https://github.com/mbocek/evidence-computer/actions/workflows/maven.yml/badge.svg)

## Build project
```
./mvnw clean install
```

Build local docker image:
```
./mvnw clean compile jib:dockerBuild
```

Run localy:
```
./mvnw clean spring-boot:run
```
