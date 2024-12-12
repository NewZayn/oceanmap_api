package org.oceanmap.serviceTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.oceanmap.dto.UserDTO;
import org.oceanmap.dto.UserLoged;
import org.oceanmap.exception.ObjectNotFound;
import org.oceanmap.model.Profile;
import org.oceanmap.model.User;
import org.oceanmap.repository.ProfileRepository;
import org.oceanmap.repository.UserRepository;
import org.oceanmap.service.UserService;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ProfileRepository profileRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindById_UserExists() {
        User user = new User(1L, "John", "john@example.com", "password", new Date(), new Date(), true, null);
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        User result = userService.findById(1L);

        assertNotNull(result);
        assertEquals("John", result.getName());
        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    void testFindById_UserNotFound() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(ObjectNotFound.class, () -> userService.findById(1L));

        assertEquals("User Not Found", exception.getMessage());
        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    void testSave_NewUserWithProfile() {
        Profile profile = new Profile();
        profile.setId(null);
        User user = new User(null, "Jane", "jane@example.com", "password", new Date(), new Date(), true, profile);

        userService.save(user);

        verify(profileRepository, times(1)).save(profile);
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void testSave_NewUserWithoutProfile() {
        User user = new User(null, "Jane", "jane@example.com", "password", new Date(), new Date(), true, null);

        userService.save(user);

        verify(profileRepository, times(0)).save(any(Profile.class));
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void testLogin_UserExists() {
        UserLoged userLoged = new UserLoged("john@example.com", "password");
        User user = new User(1L, "John", "john@example.com", "password", new Date(), new Date(), true, null);

        when(userRepository.findByEmailAndPassword("john@example.com", "password")).thenReturn(user);

        UserDTO result = userService.login(userLoged);

        assertNotNull(result);
        assertEquals("John", result.getName());
        verify(userRepository, times(1)).findByEmailAndPassword("john@example.com", "password");
    }

    @Test
    void testLogin_UserNotFound() {
        UserLoged userLoged = new UserLoged("john@example.com", "wrongpassword");

        when(userRepository.findByEmailAndPassword("john@example.com", "wrongpassword")).thenReturn(null);

        Exception exception = assertThrows(ObjectNotFound.class, () -> userService.login(userLoged));

        assertEquals("Usuário não encontrado", exception.getMessage());
        verify(userRepository, times(1)).findByEmailAndPassword("john@example.com", "wrongpassword");
    }

    @Test
    void testFindByEmail_EmailExists() {
        User user = new User(1L, "John", "john@example.com", "password", new Date(), new Date(), true, null);
        when(userRepository.findByEmail("john@example.com")).thenReturn(user);

        Boolean result = userService.findByEmail("john@example.com");

        assertTrue(result);
        verify(userRepository, times(1)).findByEmail("john@example.com");
    }

    @Test
    void testFindByEmail_EmailDoesNotExist() {
        when(userRepository.findByEmail("john@example.com")).thenReturn(null);

        Boolean result = userService.findByEmail("john@example.com");

        assertFalse(result);
        verify(userRepository, times(1)).findByEmail("john@example.com");
    }

    @Test
    void testDeleteUser() {
        Long userId = 1L;

        userService.deleteUser(userId);

        verify(userRepository, times(1)).deleteById(userId);
    }
}