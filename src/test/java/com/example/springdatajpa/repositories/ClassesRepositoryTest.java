package com.example.springdatajpa.repositories;


import com.example.springdatajpa.entities.Classes;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ClassesRepositoryTest {
    @Autowired
    private ClassesRepository classesRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    public Classes createClasses(String name) {
        return Classes.builder().name(name).build();
    }


    @Test
    public void should_return_classes_by_id() {
        final int id = 1;
        final String name = "a";
        final Classes testClasses = this.createClasses(name);
        testEntityManager.persistAndFlush(testClasses);

        Classes foundClasses = classesRepository.findById(id).orElseThrow();

        System.out.println(testClasses);
        System.out.println(foundClasses);

        assertNotNull(foundClasses);
        assertEquals(testClasses, foundClasses);
    }
}
