
/* ====================== Lombok Start ======================= */
Project Lombok v1.18.38 - Installer

-

x

Install successful

Lombok has been installed on the selected IDE installations.
Don't forget to:
. add lombok. jar to your projects,
. exit and start your IDE,
. rebuild all projects!

If you start Eclipse with a custom -vm parameter, you'll need to add:
-vmargs -javaagent : lombok. jar
as parameter as well.

. PLATFORM: JDK24 support added
FEATURE: Lombok's nullity annotation now supports JSpecify out of the box, using config key
jspecify.
. BUGFIX: Recent eclipse releases would get you 'negative length' error, The bug had always been
in lombok but didn't matter until recent releases.
. BUGFIX: The 'extract local variable' refactor script of VSCode wouldn't replace all occurrences if
run on a method call to a lombok generated method.

https://projectlombok,org

v1.18.38

View full changelog

Quit Installer

/* ====================== Lombok End ======================= */

/* ====================== Spring Boot TABLE End ======================= */
해결 방법
1) PostgreSQL 전용 스키마 직접 생성
PostgreSQL에서 실행:
CREATE TABLE SPRING_SESSION (
    PRIMARY_ID CHAR(36) NOT NULL,
    SESSION_ID CHAR(36) NOT NULL,
    CREATION_TIME BIGINT NOT NULL,
    LAST_ACCESS_TIME BIGINT NOT NULL,
    MAX_INACTIVE_INTERVAL INT NOT NULL,
    EXPIRY_TIME BIGINT NOT NULL,
    PRINCIPAL_NAME VARCHAR(100),
    CONSTRAINT SPRING_SESSION_PK PRIMARY KEY (PRIMARY_ID)
);

CREATE UNIQUE INDEX SPRING_SESSION_IX1 ON SPRING_SESSION (SESSION_ID);
CREATE INDEX SPRING_SESSION_IX2 ON SPRING_SESSION (EXPIRY_TIME);
CREATE INDEX SPRING_SESSION_IX3 ON SPRING_SESSION (PRINCIPAL_NAME);

CREATE TABLE SPRING_SESSION_ATTRIBUTES (
    SESSION_PRIMARY_ID CHAR(36) NOT NULL,
    ATTRIBUTE_NAME VARCHAR(200) NOT NULL,
    ATTRIBUTE_BYTES BYTEA NOT NULL,
    CONSTRAINT SPRING_SESSION_ATTRIBUTES_PK PRIMARY KEY (SESSION_PRIMARY_ID, ATTRIBUTE_NAME),
    CONSTRAINT SPRING_SESSION_ATTRIBUTES_FK FOREIGN KEY (SESSION_PRIMARY_ID)
        REFERENCES SPRING_SESSION (PRIMARY_ID) ON DELETE CASCADE
);


2) Spring Boot에서 자동 생성하게 설정
application.properties에:
	spring.session.jdbc.initialize-schema=always

개발 환경에서만 추천

실행 시 Spring이 자동으로 두 테이블을 만들어 줍니다

운영 환경은 직접 생성이 안전


3) 세션 저장소를 JDBC 대신 메모리로 변경 (필요 없는 경우)
DB 세션 관리가 굳이 필요 없으면:
	spring.session.store-type=none
이렇게 하면 애초에 SPRING_SESSION / SPRING_SESSION_ATTRIBUTES를 찾지 않아요.

/* ====================== Spring Boot TABLE End ======================= */


/* ====================== Spring Boot  ======================= */

Using generated security password: 4d86d49f-2789-4873-a101-989005d34956


[2m2025-08-29T17:00:50.350+09:00[0;39m [33m WARN[0;39m [35m15168[0;39m [2m--- [spring-boot-jpa-postgresql] [           main] [0;39m[36m.a.s.UserDetailsServiceAutoConfiguration[0;39m [2m:[0;39m 
Using generated security password: aaf131aa-bd71-4ccc-ac82-0c617cc23e8c

[2m2025-08-29T17:11:48.438+09:00[0;39m [33m WARN[0;39m [35m10512[0;39m [2m--- [spring-boot-jpa-postgresql] [           main] [0;39m[36m.a.s.UserDetailsServiceAutoConfiguration[0;39m [2m:[0;39m 
Using generated security password: 29c02f24-6859-4eb3-bed2-a71c080b9ef7

/* ====================== Spring Boot  ======================= */



