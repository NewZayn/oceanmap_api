
package org.oceanmap.service;

import org.oceanmap.dto.SearchHistoryDTO;
import org.oceanmap.model.SearchHistory;
import org.oceanmap.model.User;
import org.oceanmap.repository.SearchHistoryRepository;
import org.oceanmap.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;


@Service
public class SearchHistoryService {

    @Autowired
    private SearchHistoryRepository searchHistoryRepository;

    @Autowired
    private UserRepository userRepository;


    public void saveSearchHistory(SearchHistory searchHistory) {
        searchHistoryRepository.save(searchHistory);
    }

    public void RemoveSearchHistory(SearchHistory searchHistory) {
        searchHistoryRepository.delete(searchHistory);
    }

    public List<SearchHistory> findAllByid (Long userId){
        return  searchHistoryRepository.findAllById(userId);
    }

    public SearchHistory fromDTO (SearchHistoryDTO history){
       User user = userRepository.findByID(history.getUser()).orElseThrow(() -> new RuntimeException("User not found"));

        return new SearchHistory(
                null,
                user,
                history.getBeachAddress(),
                LocalDateTime.now()
        );

    }


}