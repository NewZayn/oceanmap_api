package org.oceanmap.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.oceanmap.model.UserSearchHistory;
import org.oceanmap.model.User;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SearchHistoryDTO {

    private Long id;

    private User user;

    private String beachAddress;

    private LocalDateTime searchDate;

    public SearchHistoryDTO(UserSearchHistory userSearchHistory) {
        this.id = userSearchHistory.getId();
        this.user = userSearchHistory.getUser();
        this.beachAddress = userSearchHistory.getBeachAddress();
        this.searchDate = userSearchHistory.getSearchDate();

    }
}
