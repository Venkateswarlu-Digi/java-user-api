package com.gmmco.user.userServiceTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.gmmco.user.model.User;
import com.gmmco.user.repository.UserRepository;
import com.gmmco.user.serviceImplementation.UserServiceImplementation;

@ExtendWith(MockitoExtension.class)
class UserServiceImplementationTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImplementation userService;

    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
        user.id = 1;
        user.empId = "gb5hff77";
        user.name = "Rakesh";
        user.email = "rakesh@gmail.com";
    }

    @Test
    void testGetUserByEmpId() {

        when(userRepository.findByEmpId("gb5hff77"))
                .thenReturn(Optional.of(user));

        User result = userService.getUser("gb5hff77", null);

        assertNotNull(result);
        assertEquals("gb5hff77", result.empId);
        assertEquals("Rakesh", result.name);

        verify(userRepository, times(1)).findByEmpId("gb5hff77");
    }

    @Test
    void testGetUserByEmail() {

        when(userRepository.findByEmail("rakesh@gmail.com"))
                .thenReturn(Optional.of(user));

        User result = userService.getUser(null, "rakesh@gmail.com");

        assertNotNull(result);
        assertEquals("rakesh@gmail.com", result.email);

        verify(userRepository, times(1)).findByEmail("rakesh@gmail.com");
    }

    @Test
    void testUserNotFoundByEmpId() {

        when(userRepository.findByEmpId("invalid"))
                .thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> userService.getUser("invalid", null));

        assertEquals("User not found", ex.getMessage());
    }

    @Test
    void testUserNotFoundByEmail() {

        when(userRepository.findByEmail("abc@gmail.com"))
                .thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> userService.getUser(null, "abc@gmail.com"));

        assertEquals("User not found", ex.getMessage());
    }

    @Test
    void testNoInputProvided() {

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> userService.getUser(null, null));

        assertEquals("Please provide either id or email", ex.getMessage());
    }
}