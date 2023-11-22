package os.spotlight.link.adapter.jira.software.cloud.rest.api.mapper;

import jira.software.cloud.rest.api.boards.GetAllBoardsResponse;
import jira.software.cloud.rest.api.boards.JiraBoard;
import jira.software.cloud.rest.api.issues.GetAllItemsResponse;
import jira.software.cloud.rest.api.issues.JiraIssue;
import jira.software.cloud.rest.api.sprints.GetAllGroupsResponse;
import jira.software.cloud.rest.api.sprints.JiraSprint;
import os.spotlight.persistance.entity.Board;
import os.spotlight.persistance.entity.Group;
import os.spotlight.persistance.entity.Item;

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

    public static List<Item> of(GetAllItemsResponse response) {
        return response.getIssues().stream()
                .map(JiraMapper::of)
                .toList();
    }

    private static Item of(JiraIssue issue) {
        var issueName = String.format("%s : %s", issue.getKey(), issue.getFields().getSummary());
        return Item.builder()
                .id(issue.getId())
                .name(issueName)
                .estimation(String.valueOf(issue.getFields().getCustomfield10016()))
                .status(issue.getFields().getStatus().getName())
                .build();
    }
}
