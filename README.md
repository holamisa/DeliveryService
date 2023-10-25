# DeliveryService

*** 간단한 배달 시스템 ***

프로젝트 설명
- 사용자(소비자) 등록, 조회, 삭제
- Interceptor + Resolver 통한 사용자(소비자) 인증
- 가맹점 등록, 조회, 삭제
- Spring Security 통한 사용자(가맹점) 인증
- 가맹점 메뉴 등록, 조회, 삭제
- 가맹점 사용자 등록, 조회, 삭제
- 한 가맹점의 메뉴 리스트 주문 등록, 조회
- RabbitMq 이용하여 소비자(Producer) 주문 등록 후 가맹점(Consumer) 메시징 처리
- SSE 이용하여 가맹점 클라이언트에 알림 전송

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
