## 1. How to run on Local environment

- change datasource url in application.yml

    ```yaml
    datasource:
        driver-class-name: com.mysql.cj.jdbc.Driver
        url: jdbc:mysql://localhost:3307/polin   # 3307 -> 3306
        username: root
        password: polin1111
    ```

- Install redis
- run `SpringBootApplication.java` in IntelliJ IDEA

## 2. How to run on Docker environment

- docker-compose build

    ```
    docker-compose up -d --build
    ```

- run `SpringBootApplication.java` in IntelliJ IDEA