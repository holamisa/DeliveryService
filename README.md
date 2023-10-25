# DeliveryService

*** 간단한 배달 시스템 ***

Framework : Spring Boot 2.7.x, Spring Security

Build Tool : Gradle, Groovy

Language : Java 11

Database Library : JPA

Database Server : MySQL 8.x

설정
1. Multi module project
2. Custom object mapper
3. Swagger ui
4. Filter, Interceptor, TimerAop
5. Validation
6. Security Authorizing
7. RabbitMQ
    - API Server(Publisher) -> Exchange -> Queue <-> StoreAdmin Server(Consumer)
8. SSE (Server-Sent Events)
   - Store Admin Server -> Store Client

규칙
1. JSON SnakeCase Naming
