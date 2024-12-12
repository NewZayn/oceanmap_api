package org.oceanmap.serviceTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.oceanmap.dto.SearchHistoryDTO;
import org.oceanmap.model.SearchHistory;
import org.oceanmap.model.User;
import org.oceanmap.repository.SearchHistoryRepository;
import org.oceanmap.repository.UserRepository;
import org.oceanmap.service.SearchHistoryService;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SearchHistoryServiceTest {

    @InjectMocks
    private SearchHistoryService searchHistoryService;

    @Mock
    private SearchHistoryRepository searchHistoryRepository;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveSearchHistory() {
        SearchHistory searchHistory = new SearchHistory(1L, new User(), "Beach A", LocalDateTime.now());

        searchHistoryService.saveSearchHistory(searchHistory);

        verify(searchHistoryRepository, times(1)).save(searchHistory);
    }

    @Test
    void testRemoveSearchHistory() {
        SearchHistory searchHistory = new SearchHistory(1L, new User(), "Beach B", LocalDateTime.now());

        searchHistoryService.RemoveSearchHistory(searchHistory);

        verify(searchHistoryRepository, times(1)).delete(searchHistory);
    }

    @Test
    void testFindAllById() {
        Long userId = 1L;
        SearchHistory searchHistory1 = new SearchHistory(1L, new User(), "Beach A", LocalDateTime.now());
        SearchHistory searchHistory2 = new SearchHistory(2L, new User(), "Beach B", LocalDateTime.now());
        List<SearchHistory> searchHistories = Arrays.asList(searchHistory1, searchHistory2);

        when(searchHistoryRepository.findAllById(userId)).thenReturn(searchHistories);

        List<SearchHistory> result = searchHistoryService.findAllByid(userId);

        assertEquals(2, result.size());
        assertEquals("Beach A", result.getFirst().getBeachAddress());
        verify(searchHistoryRepository, times(1)).findAllById(userId);
    }

    @Test
    void testFromDTO() {
        Long userId = 1L;
        SearchHistoryDTO dto = new SearchHistoryDTO(null,userId, "Beach C", LocalDateTime.now());
        User user = new User();
        user.setId(userId);
        when(userRepository.findByID(userId)).thenReturn(Optional.of(user));

        SearchHistory result = searchHistoryService.fromDTO(dto);

        assertNotNull(result);
        assertEquals("Beach C", result.getBeachAddress());
        assertNotNull(result.getUser());
        assertEquals(userId, result.getUser().getId());
        verify(userRepository, times(1)).findByID(userId);
    }


    @Test
    void testFromDTOUserNotFound() {
        Long userId = 1L;
        SearchHistoryDTO dto = new SearchHistoryDTO(null,userId, "Beach C", LocalDateTime.now());
        when(userRepository.findByID(userId)).thenReturn(Optional.empty());
        Exception exception = assertThrows(RuntimeException.class, () -> searchHistoryService.fromDTO(dto));
        assertEquals("User not found", exception.getMessage());
        verify(userRepository, times(1)).findByID(userId);
    }
}
