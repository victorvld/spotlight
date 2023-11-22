package os.spotlight.link.adapter.monday.graph.ql.api.mapper;

import monday.graph.ql.api.boards.GetAllBoardsResponse;
import monday.graph.ql.api.groups.GetAllGroupsResponse;
import monday.graph.ql.api.items.GetAllItemsResponse;
import os.spotlight.persistance.entity.Board;
import os.spotlight.persistance.entity.Group;
import os.spotlight.persistance.entity.Item;

import java.util.List;
import java.util.Objects;

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

    public static List<Item> of(GetAllItemsResponse response) {
        return response.getData().getBoards().get(0).getGroups().get(0).getItems().stream().map(MondayMapper::of)
                .toList();
    }

    private static Item of(monday.graph.ql.api.items.Item response) {
        var status = response.getColumnValues().stream().filter(columnValue -> Objects.equals(columnValue.getId(), "task_status")).toList().get(0).getText();
        var estimation = response.getColumnValues().stream().filter(columnValue -> Objects.equals(columnValue.getId(), "task_estimation")).toList().get(0).getText();
        return Item.builder()
                .id(response.getId())
                .name(response.getName())
                .status(status)
                .estimation(estimation)
                .build();
    }
}
