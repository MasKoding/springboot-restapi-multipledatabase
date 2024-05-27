# Spring Boot Framework Multiple Database

Hello pada kali ini saya akan membagikan kepada kalian project Rest API menggunakan framework Spring Boot dengan koneksi multiple database
<br/><br/>
Pada case kali ini saya menggunakan Database MySQL dan DB2 IBM

# Installasi
- Clone project : git clone https://github.com/MasKoding/spring-restful-api.git
- Install Java (version:JDK 17)
- Install Docker
- Install Dbever
- Install Laragon
- Install Intellij IDEA Community Edition (Tools IDE)


# Dependencies (pom.xml)
1. Dependency dibawah digunakan agar kita bisa menggunakan driver dari IBM DB2 
```xml
   <dependency>
    <groupId>com.ibm.db2</groupId>
    <artifactId>jcc</artifactId>
    <version>11.1.4.4</version>
</dependency>
```
2. Dependency dibawah digunakan agar kita bisa menggunakan driver dari MYSQL
 ```xml
    <dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <scope>runtime</scope>
    <version>8.0.30</version>
</dependency> 
```

# Configuration Properties
Berikut beberapa properties yang perlu ditambahkan ada di file application.properties
```
    server.port=8085
spring.datasource.mysql.jdbc-url=jdbc:mysql://localhost:3306/coba
spring.datasource.mysql.username=root
spring.datasource.mysql.password=
spring.datasource.mysql.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.mysql.hibernate.dialect= org.hibernate.dialect.MySQLDialect
spring.jpa.mysql.hibernate.ddl-auto=update

spring.datasource.db2.jdbc-url=jdbc:db2://localhost:50000/testdb
spring.datasource.db2.username=db2inst1
spring.datasource.db2.password=password
spring.datasource.db2.driver-class-name=com.ibm.db2.jcc.DB2Driver
spring.jpa.db2.hibernate.dialect=org.hibernate.dialect.DB2Dialect
spring.jpa.db2.hibernate.ddl-auto=update

```

# Running Project
```Klik ▶ Spring Boot Application```