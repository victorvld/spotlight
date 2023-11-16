package os.spotlight.link.adapter.jira.software.cloud.rest.api.mapper;

import jira.software.cloud.rest.api.GetAllBoardsResponse;
import jira.software.cloud.rest.api.GetAllGroupsResponse;
import jira.software.cloud.rest.api.JiraBoard;
import jira.software.cloud.rest.api.JiraSprint;
import os.spotlight.persistance.entity.Board;
import os.spotlight.persistance.entity.Group;

import java.util.List;

public class JiraMapper {
    private JiraMapper() {
    }

    public static List<Board> of(GetAllBoardsResponse raw) {
        return raw.getValues().stream()
                .map(JiraMapper::of)
                .toList();
    }

    private static Board of(JiraBoard raw) {
        return Board.builder()
                .boardId(String.valueOf(raw.getId()))
                .boardName(raw.getName())
                .build();
    }

    public static List<Group> of(GetAllGroupsResponse response) {
        return response.getValues().stream()
                .map(JiraMapper::of)
                .toList();
    }

    private static Group of(JiraSprint sprint) {
        return Group.builder()
                .groupName(String.valueOf(sprint.getName()))
                .groupId(String.valueOf(sprint.getId()))
                .build();
    }
}
