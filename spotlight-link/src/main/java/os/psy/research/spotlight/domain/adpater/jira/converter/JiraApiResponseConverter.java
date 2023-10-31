package os.psy.research.spotlight.domain.adpater.jira.converter;

import os.psy.research.spotlight.domain.adpater.jira.mappers.GetAllBoardsResponse;
import os.psy.research.spotlight.domain.entity.Board;

import java.util.Collections;
import java.util.List;

public class JiraApiResponseConverter {
    private JiraApiResponseConverter() {
    }

    public static List<Board> convertToBoards(GetAllBoardsResponse response) {
        if (response == null || response.getValues() == null)
            return Collections.emptyList();
        return response.getValues().stream()
                .map(JiraApiResponseConverter::convertToBoards)
                .toList();
    }

    private static Board convertToBoards(GetAllBoardsResponse.Board response) {
        return Board.builder()
                .boardId(String.valueOf(response.getId()))
                .boardName(response.getName())
                .avatarUri(response.getLocation() != null ? response.getLocation().getAvatarURI() : null)
                .projectName(response.getLocation() != null ? response.getLocation().getProjectName() : null)
                .projectKey(response.getLocation() != null ? response.getLocation().getProjectKey() : null)
                .build();
    }
}
