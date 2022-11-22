## 1. How to run on Local environment

- change datasource url in application.yml

    ```yaml
    datasource:
        driver-class-name: com.mysql.cj.jdbc.Driver
        url: jdbc:mysql://localhost:3307/polin   # 3307 -> 3306
        username: {USER_NAME}
        password: {USER_PASSWORD}
    ```

- Install redis
- run `SpringBootApplication.java` in IntelliJ IDEA

## 2. How to run on Docker environment

- make bootJar file

- docker-compose build

    ```
    docker-compose up -d --build
    ```
