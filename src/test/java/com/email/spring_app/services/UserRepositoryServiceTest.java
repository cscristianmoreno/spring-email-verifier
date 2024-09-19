package com.email.spring_app.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.email.spring_app.annotations.SpringBootTestInitializer;
import com.email.spring_app.entity.Users;

@SpringBootTestInitializer
public class UserRepositoryServiceTest {

    @Autowired
    private UserRepositoryService userRepositoryService;

    private Users users;

    @BeforeEach
    void setup() {
        users = new Users();
        users.setUsername("user");
        users.setPassword("user");
        users.setEmail("example@gmail.com");
    }

    @Test
    void testSave() {
        Users result = userRepositoryService.save(users);
        System.out.println(result);
        assertNotNull(result);
    }

    @Test
    void testUpdate() {
        Users saved = userRepositoryService.save(users);
        
        Optional<Users> find = userRepositoryService.findById(1);
        Users result = find.get();
        result.setEmail("change@gmail.com");

        Users update = userRepositoryService.update(result);
        
        assertNotNull(update);
        assertNotEquals(users.getEmail(), update.getEmail());
    }

    @Test
    void testFindById() {
        userRepositoryService.save(users);

        Optional<Users> result = userRepositoryService.findById(1);

        assertNotNull(result);
        assertTrue(result.isPresent());
    }

    @Test
    void testFindByUsername() {
        userRepositoryService.save(users);

        Optional<Users> result = userRepositoryService.findByUsername(users.getUsername());

        assertNotNull(result);
        assertTrue(result.isPresent());
        assertEquals(users.getUsername(), result.get().getUsername());
    }

    @Test
    void testDeleteById() {
        userRepositoryService.deleteById(1);
    }
}
