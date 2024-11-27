package org.oceanmap.controller;


import org.oceanmap.dto.SearchHistoryDTO;
import org.oceanmap.model.SearchHistory;
import org.oceanmap.service.SearchHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/api/{userId}")
@RestController
public class UserSearchController {

    @Autowired
    SearchHistoryService searchHistoryService;

    @GetMapping("/history")
    public ResponseEntity<List<SearchHistoryDTO>> findByAllId(@PathVariable Long userId){
       List<SearchHistoryDTO> searchHistoryDTOS = searchHistoryService.findAllByid(userId).stream().map(SearchHistoryDTO::new).collect(Collectors.toList());
       return  ResponseEntity.ok().body(searchHistoryDTOS);
    }

    @PostMapping("/saveSearchHistory")
    public ResponseEntity<SearchHistoryDTO> saveSearchHistory(@RequestBody SearchHistoryDTO searchHistoryDTO, @PathVariable Long userId){
         SearchHistory searchHistory =  searchHistoryService.fromDTO(searchHistoryDTO);
         searchHistoryService.saveSearchHistory(searchHistory);
         return ResponseEntity.ok().body(new SearchHistoryDTO(searchHistory));
    }



}
