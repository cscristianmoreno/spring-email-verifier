package com.email.spring_app.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;

@Retention(RetentionPolicy.RUNTIME)
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestPropertySource(locations = "classpath:test.yml")
@SqlConfig(separator = "^^^ END OF SCRIPT ^^^")
@Sql({
    "classpath:sql/schema.sql",
    "classpath:sql/tables.sql",
    "classpath:sql/functions.sql",

})
public @interface SpringBootTestInitializer {
    
}
