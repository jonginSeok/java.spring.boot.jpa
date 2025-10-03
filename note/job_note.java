

/* ====================== VSCODE config ======================= */
    "launch": {

        "configurations": [],
        "compounds": []
    },
    "workbench.startupEditor": "none",
    "java.jdt.ls.java.home": "",

    "java.configuration.runtimes": [
        {
            "name": "JavaSE-1.8",
            "path": "C:\\Program Files\\Eclipse Adoptium\\jdk-8.0.352.8-hotspot"
        },
        {
            "name": "JavaSE-11",
            "path": "C:\\Program Files\\Eclipse Adoptium\\jdk-11.0.17.8-hotspot",
            "default": true
        }
    ]
}
/* ====================== VSCODE config ======================= */
    
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
[2m2025-09-21T04:01:32.152+09:00[0;39m [33m WARN[0;39m [35m13612[0;39m [2m--- [spring-boot-jpa-postgresql] [           main] [0;39m[36mJpaBaseConfiguration$JpaWebConfiguration[0;39m [2m:[0;39m spring.jpa.open-in-view is enabled by default. Therefore, database queries may be performed during view rendering. Explicitly configure spring.jpa.open-in-view to disable this warning
[2m2025-09-21T04:01:32.521+09:00[0;39m [33m WARN[0;39m [35m13612[0;39m [2m--- [spring-boot-jpa-postgresql] [           main] [0;39m[36m.a.s.UserDetailsServiceAutoConfiguration[0;39m [2m:[0;39m 

Using generated security password: 77cb284c-23be-45ab-a579-05c58482db71

This generated password is for development use only. Your security configuration must be updated before running your application in production.











[2m2025-09-21T04:02:00.045+09:00[0;39m [31mERROR[0;39m [35m13612[0;39m [2m--- [spring-boot-jpa-postgresql] [pring-session-1] [0;39m[36mo.s.s.s.TaskUtils$LoggingErrorHandler   [0;39m [2m:[0;39m Unexpected error occurred in scheduled task

org.springframework.jdbc.BadSqlGrammarException: PreparedStatementCallback; bad SQL grammar [DELETE FROM SPRING_SESSION
WHERE EXPIRY_TIME < ?
]
	at org.springframework.jdbc.support.SQLErrorCodeSQLExceptionTranslator.doTranslate(SQLErrorCodeSQLExceptionTranslator.java:244) ~[spring-jdbc-7.0.0-M9.jar:7.0.0-M9]
	at org.springframework.jdbc.support.AbstractFallbackSQLExceptionTranslator.translate(AbstractFallbackSQLExceptionTranslator.java:102) ~[spring-jdbc-7.0.0-M9.jar:7.0.0-M9]
	at org.springframework.jdbc.core.JdbcTemplate.translateException(JdbcTemplate.java:1559) ~[spring-jdbc-7.0.0-M9.jar:7.0.0-M9]
	at org.springframework.jdbc.core.JdbcTemplate.execute(JdbcTemplate.java:692) ~[spring-jdbc-7.0.0-M9.jar:7.0.0-M9]
	at org.springframework.jdbc.core.JdbcTemplate.update(JdbcTemplate.java:976) ~[spring-jdbc-7.0.0-M9.jar:7.0.0-M9]
	at org.springframework.jdbc.core.JdbcTemplate.update(JdbcTemplate.java:1020) ~[spring-jdbc-7.0.0-M9.jar:7.0.0-M9]
	at org.springframework.jdbc.core.JdbcTemplate.update(JdbcTemplate.java:1030) ~[spring-jdbc-7.0.0-M9.jar:7.0.0-M9]
	at org.springframework.session.jdbc.JdbcIndexedSessionRepository.lambda$cleanUpExpiredSessions$8(JdbcIndexedSessionRepository.java:649) ~[spring-session-jdbc-4.0.0-M2.jar:4.0.0-M2]
	at org.springframework.transaction.support.TransactionTemplate.execute(TransactionTemplate.java:137) ~[spring-tx-7.0.0-M9.jar:7.0.0-M9]
	at org.springframework.session.jdbc.JdbcIndexedSessionRepository.cleanUpExpiredSessions(JdbcIndexedSessionRepository.java:648) ~[spring-session-jdbc-4.0.0-M2.jar:4.0.0-M2]
	at org.springframework.scheduling.support.DelegatingErrorHandlingRunnable.run(DelegatingErrorHandlingRunnable.java:54) ~[spring-context-7.0.0-M9.jar:7.0.0-M9]
	at org.springframework.scheduling.concurrent.ReschedulingRunnable.run(ReschedulingRunnable.java:94) ~[spring-context-7.0.0-M9.jar:7.0.0-M9]
	at java.base/java.util.concurrent.Executors$RunnableAdapter.call(Executors.java:545) ~[na:na]
	at java.base/java.util.concurrent.FutureTask.run(FutureTask.java:328) ~[na:na]
	at java.base/java.util.concurrent.ScheduledThreadPoolExecutor$ScheduledFutureTask.run(ScheduledThreadPoolExecutor.java:309) ~[na:na]
	at java.base/java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1090) ~[na:na]
	at java.base/java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:614) ~[na:na]
	at java.base/java.lang.Thread.run(Thread.java:1474) ~[na:na]
Caused by: org.postgresql.util.PSQLException: 오류: "spring_session" 이름의 릴레이션(relation)이 없습니다
  Position: 13
	at org.postgresql.core.v3.QueryExecutorImpl.receiveErrorResponse(QueryExecutorImpl.java:2734) ~[postgresql-42.7.7.jar:42.7.7]
	at org.postgresql.core.v3.QueryExecutorImpl.processResults(QueryExecutorImpl.java:2421) ~[postgresql-42.7.7.jar:42.7.7]
	at org.postgresql.core.v3.QueryExecutorImpl.execute(QueryExecutorImpl.java:372) ~[postgresql-42.7.7.jar:42.7.7]
	at org.postgresql.jdbc.PgStatement.executeInternal(PgStatement.java:518) ~[postgresql-42.7.7.jar:42.7.7]
	at org.postgresql.jdbc.PgStatement.execute(PgStatement.java:435) ~[postgresql-42.7.7.jar:42.7.7]
	at org.postgresql.jdbc.PgPreparedStatement.executeWithFlags(PgPreparedStatement.java:196) ~[postgresql-42.7.7.jar:42.7.7]
	at org.postgresql.jdbc.PgPreparedStatement.executeUpdate(PgPreparedStatement.java:157) ~[postgresql-42.7.7.jar:42.7.7]
	at com.zaxxer.hikari.pool.ProxyPreparedStatement.executeUpdate(ProxyPreparedStatement.java:61) ~[HikariCP-7.0.2.jar:na]
	at com.zaxxer.hikari.pool.HikariProxyPreparedStatement.executeUpdate(HikariProxyPreparedStatement.java) ~[HikariCP-7.0.2.jar:na]
	at org.springframework.jdbc.core.JdbcTemplate.lambda$update$0(JdbcTemplate.java:981) ~[spring-jdbc-7.0.0-M9.jar:7.0.0-M9]
	at org.springframework.jdbc.core.JdbcTemplate.execute(JdbcTemplate.java:673) ~[spring-jdbc-7.0.0-M9.jar:7.0.0-M9]
	... 14 common frames omitted



