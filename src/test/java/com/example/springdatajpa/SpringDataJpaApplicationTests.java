package com.example.springdatajpa;

import com.example.springdatajpa.entities.Classes;
import com.example.springdatajpa.entities.User;
import com.example.springdatajpa.repositories.ClassesRepository;
import com.example.springdatajpa.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SpringDataJpaApplicationTests {
    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ClassesRepository classesRepository;


    public User createUser(String name, Classes classes) {
        return User.builder().name(name).classes(classes).build();
    }

    public Classes createClasses(String name) {
        return Classes.builder().name(name).build();
    }

    @Test
    @Transactional
    public void should_return_user_by_id() {
        final String user_name = "aa";

        final String classes_name = "1班";
        final Classes classes = this.createClasses(classes_name);
        classesRepository.save(classes);

        Optional<Classes> optionalClasses = classesRepository.findById(classes.getId());
        if (optionalClasses.isPresent()) {
            Classes foundClasses = optionalClasses.get();

            User testUser = this.createUser(user_name, foundClasses);
            userRepository.save(testUser);

            System.out.println(userRepository.findById(testUser.getId()).get());

            UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromPath("/user/info").queryParam("id", testUser.getId());
            ResponseEntity<User> responseEntity = testRestTemplate.exchange(uriBuilder.toUriString(), HttpMethod.GET, null, User.class);

            assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
            assertEquals(responseEntity.getHeaders().getContentType(), MediaType.APPLICATION_JSON);
            User foundUser = responseEntity.getBody();
            System.out.println(foundUser);
            assertNotNull(foundUser);
            assertEquals(foundUser, testUser);
        } else {
            System.out.println("Error");
        }
    }
}
