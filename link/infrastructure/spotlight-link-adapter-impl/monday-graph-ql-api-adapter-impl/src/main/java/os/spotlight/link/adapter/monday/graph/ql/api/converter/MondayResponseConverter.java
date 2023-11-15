package os.spotlight.link.adapter.monday.graph.ql.api.converter;

import monday.graph.ql.api.GetAllGroupsResponse;
import os.spotlight.persistance.entity.Board;
import os.spotlight.link.adapter.monday.graph.ql.api.mapper.GetAllBoardsResponse;
import os.spotlight.persistance.entity.Group;

import java.util.List;

public class MondayResponseConverter {

    private MondayResponseConverter() {
    }

    public static List<Board> convertToBoards(GetAllBoardsResponse response) {
        return response.getData().getBoards().stream().map(MondayResponseConverter::convertToBoards)
                .toList();
    }

    private static Board convertToBoards(GetAllBoardsResponse.Data.Board response) {
        return Board.builder()
                .boardId(String.valueOf(response.getId()))
                .boardName(response.getName())
                .build();
    }

    public static List<Group> convertToGroups(GetAllGroupsResponse response) {
        return response.getData().getBoards().get(0).getGroups().stream().map(MondayResponseConverter::convertToGroups)
                .toList();
    }

    private static Group convertToGroups(monday.graph.ql.api.Group response) {
        return Group.builder()
                .groupId(response.getId())
                .groupName(response.getTitle())
                .build();
    }
}
