package os.spotlight.link.adapter.monday.graph.ql.api.mapper;

import monday.graph.ql.api.boards.GetAllBoardsResponse;
import monday.graph.ql.api.groups.GetAllGroupsResponse;
import os.spotlight.persistance.entity.Board;
import os.spotlight.persistance.entity.Group;

import java.util.List;

public class MondayMapper {

    private MondayMapper() {
    }

    public static List<Board> of(GetAllBoardsResponse response) {
        return response.getData().getBoards().stream().map(MondayMapper::of)
                .toList();
    }

    private static Board of(monday.graph.ql.api.boards.Board response) {
        return Board.builder()
                .boardId(String.valueOf(response.getId()))
                .boardName(response.getName())
                .build();
    }

    public static List<Group> of(GetAllGroupsResponse response) {
        return response.getData().getBoards().get(0).getGroups().stream().map(MondayMapper::of)
                .toList();
    }

    private static Group of(monday.graph.ql.api.groups.Group response) {
        return Group.builder()
                .groupId(response.getId())
                .groupName(response.getTitle())
                .build();
    }
}
