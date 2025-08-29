
// 오류 //

[2m2025-08-29T16:47:04.637+09:00[0;39m [31mERROR[0;39m [35m10984[0;39m [2m--- [spring-boot-jpa-postgresql] [nio-8080-exec-6] [0;39m[36mo.a.c.c.C.[.[.[/].[dispatcherServlet]   [0;39m [2m:[0;39m Servlet.service() for servlet [dispatcherServlet] threw exception

org.springframework.jdbc.BadSqlGrammarException: PreparedStatementCallback; bad SQL grammar [INSERT INTO SPRING_SESSION_ATTRIBUTES (SESSION_PRIMARY_ID, ATTRIBUTE_NAME, ATTRIBUTE_BYTES)
VALUES (?, ?, ?)
]

[2m2025-08-29T16:47:04.637+09:00[0;39m [31mERROR[0;39m [35m10984[0;39m [2m--- [spring-boot-jpa-postgresql] [nio-8080-exec-6] [0;39m[36mo.a.c.c.C.[Tomcat].[localhost]          [0;39m [2m:[0;39m Exception Processing [ErrorPage[errorCode=0, location=/error]]

org.springframework.jdbc.BadSqlGrammarException: PreparedStatementCallback; bad SQL grammar [INSERT INTO SPRING_SESSION_ATTRIBUTES (SESSION_PRIMARY_ID, ATTRIBUTE_NAME, ATTRIBUTE_BYTES)
VALUES (?, ?, ?)
]

Caused by: org.postgresql.util.PSQLException: ERROR: relation "spring_session_attributes" does not exist
  Position: 13