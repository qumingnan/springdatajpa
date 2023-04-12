package com.example.springdatajpa.services;
import com.example.springdatajpa.entities.User;
import com.example.springdatajpa.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
// 使用这个注解，表示这个类中使用了 Mockito
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserService userService;
    @Test
    public void test_getUserById_mockito() {
        var id = 123;
        when(userRepository.findById(id)).thenReturn(Optional.ofNullable(User.builder().id(id).name("abc").build()));

        User user = userService.getUserById(id);

        assertEquals(id, user.getId());
        Mockito.verify(userRepository).findById(id);
    }
}