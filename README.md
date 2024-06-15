# SpringBoot_RESTful_Api

## 목차
- Section 0: Web Service & Web Application
- Section 1: Spring Boot 로 개발하는 RESTful API
- Section 2: User SErvice API 추가
- Section 3: RESTful Service 기능 확장
- Section 4: Spring Boot API 사용
- Section 5: JAP
- Section 6: RESTful 설계



## 0. Web Service & Web Application
1. Web Service:  네트워크 상에서 서로 다른 종류의 컴퓨터들 간에 상호작용하기 위한 소프트웨어 시스템
2. Web Application : 서버에 저장되어 있고 웹 브라우저를 이용해서 실행할 수 있는 애플리케이션
    <image src ="https://github.com/seoin1223/SpringBoot_RESTful_Api/assets/129828463/c07cfd34-0557-424b-83cb-2baae2b21944" width = "50%" height="300px"/>

3. SOAP (Simple Object Access Protocol)
     - HTTP, HTTPS, SMTP 등을 이용해서 XML 기반의 메시지를 컴퓨터 네트워크상에서 교환하는 형태의 프로토콜
     - XML RPC
   <image src ="https://github.com/seoin1223/SpringBoot_RESTful_Api/assets/129828463/c86669d1-bc36-403e-8ddc-1684dd05d6a3" width = "50%" height="300px"/>

4. REST ( Representational State Transfer)
   - resource의 Representation에 의한 상태 전달
   - HTTP Method를 통해 Resource를 처리하기 위한 아키텍처
5. RESTful : REST API를 제공하는 웹 서비스
6. Resource
    - URL : 인터넷 자원을 나타내는 유일한 주소
    - XML, HTML, JSON
7.EndPoing : API를 통해 서버가 제공하는 리소스에 접근하기 위해서 제공되는 주소
8. OpenAPI
   - https://www.openapis.org/
   - https://spec.openapis.org/oas/v3.1.0
   - https://www.openapis.org/blog/2021/02/16/migrating-form-openapi-3-0-to-3-1-0
