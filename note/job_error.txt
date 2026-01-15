[오류]
		
/* ============================================================ */
2025-08-29T16:47:04.637+09:00 ERROR 10984 --- [spring-boot-jpa-postgresql] [nio-8080-exec-6] o.a.c.c.C.[.[.[/].[dispatcherServlet]    : Servlet.service() for servlet [dispatcherServlet] threw exception
org.springframework.jdbc.BadSqlGrammarException: PreparedStatementCallback; bad SQL grammar [INSERT INTO SPRING_SESSION_ATTRIBUTES (SESSION_PRIMARY_ID, ATTRIBUTE_NAME, ATTRIBUTE_BYTES)
VALUES (?, ?, ?)
]
2025-08-29T16:47:04.637+09:00 ERROR 10984 --- [spring-boot-jpa-postgresql] [nio-8080-exec-6] o.a.c.c.C.[Tomcat].[localhost]           : Exception Processing [ErrorPage[errorCode=0, location=/error]]
org.springframework.jdbc.BadSqlGrammarException: PreparedStatementCallback; bad SQL grammar [INSERT INTO SPRING_SESSION_ATTRIBUTES (SESSION_PRIMARY_ID, ATTRIBUTE_NAME, ATTRIBUTE_BYTES)
VALUES (?, ?, ?)
]
Caused by: org.postgresql.util.PSQLException: ERROR: relation "spring_session_attributes" does not exist
  Position: 13


/* ============================================================ */  
2025-09-21T17:17:15.818+09:00  WARN 5464 --- [spring-boot-jpa-postgresql] [           main] JpaBaseConfiguration$JpaWebConfiguration : spring.jpa.open-in-view is enabled by default. Therefore, database queries may be performed during view rendering. Explicitly configure spring.jpa.open-in-view to disable this warning
2025-09-21T17:17:16.240+09:00  WARN 5464 --- [spring-boot-jpa-postgresql] [           main] .a.s.UserDetailsServiceAutoConfiguration :

	
/* ============================================================ */	
2025-09-21T17:39:12.818+09:00  WARN 23388 --- [spring-boot-jpa-postgresql] [           main] ConfigServletWebServerApplicationContext : Exception encountered during context initialization - cancelling refresh attempt: org.springframework.beans.factory.UnsatisfiedDependencyException: Error creating bean with name 'org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration': Unsatisfied dependency expressed through method 'setFilterChains' parameter 0: Error creating bean with name 'filterChain' defined in class path resource [com/ngins/spring/jpa/postgresql/SecurityConfig.class]: Failed to instantiate [org.springframework.security.web.SecurityFilterChain]: Factory method 'filterChain' threw exception with message: Cannot invoke "org.springframework.security.config.Customizer.customize(Object)" because "formLoginCustomizer" is null


/* ============================================================ */
No grammar constraints (DTD or XML Schema). [NoGrammarConstraints]
		=> <!DOCTYPE configuration>
 