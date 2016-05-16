# Authentication

This is a simple application based on Java 8, WebSocket, Spring Boot, Hibernate technologies. Used STOMP over WebSocket,
used default Spring message Broker. Not used redis for storing sessions,
not used custom broker (Apache ActiveMQ, RabbitMQ etc) and tests not wrote (simple logic, has gui). :)

### Installation

1. clone this project
2. navigate to folder "authentication"
3. execute command ./gradlew bootRun
4. open http://localhost:8080 in your browser
5. insert fpi@bk.ru 123123 for success request
6. insert another data for fail request
