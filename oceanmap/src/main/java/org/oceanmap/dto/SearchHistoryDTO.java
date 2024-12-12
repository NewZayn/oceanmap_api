package org.oceanmap.dto;


import lombok.*;
import org.oceanmap.model.SearchHistory;
import org.oceanmap.model.User;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SearchHistoryDTO {

    private Long id;

    private Long user;

    private String beachAddress;

    private LocalDateTime searchDate;

    public SearchHistoryDTO(SearchHistory userSearchHistory) {
        this.id = userSearchHistory.getId();
        this.user = userSearchHistory.getUser().getId();
        this.beachAddress = userSearchHistory.getBeachAddress();
        this.searchDate = userSearchHistory.getSearchDate();

    }
}
