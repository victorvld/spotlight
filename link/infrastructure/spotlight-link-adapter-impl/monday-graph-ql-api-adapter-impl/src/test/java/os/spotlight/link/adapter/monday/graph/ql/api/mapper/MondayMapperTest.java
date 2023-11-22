package os.spotlight.link.adapter.monday.graph.ql.api.mapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import os.spotlight.link.adapter.monday.graph.ql.api.test.data.factory.MondayResponsesOm;

class MondayMapperTest {

    @Test
    void convertToBoards() {
        var getAllBoardssResponse = MondayResponsesOm.Boards.complete().build();
        var board = getAllBoardssResponse.getData().getBoards().get(0);

        var result = MondayMapper.of(getAllBoardssResponse);

        Assertions.assertEquals(board.getId(), result.get(0).boardId());
        Assertions.assertEquals(board.getName(), result.get(0).boardName());
    }

    @Test
    void convertToGroups() {
        var getAllGroupsResponse = MondayResponsesOm.Groups.complete().build();
        var group = getAllGroupsResponse.getData().getBoards().get(0).getGroups().get(0);

        var result = MondayMapper.of(getAllGroupsResponse);

        Assertions.assertEquals(group.getId(), result.get(0).groupId());
        Assertions.assertEquals(group.getTitle(), result.get(0).groupName());
    }

    @Test
    void convertToItems() {
        var getAllItemsResponse = MondayResponsesOm.Items.complete().build();
        var item = getAllItemsResponse.getData().getBoards().get(0).getGroups().get(0).getItems().get(0);

        var result = MondayMapper.of(getAllItemsResponse);

        Assertions.assertEquals(item.getId(), result.get(0).id());
        Assertions.assertEquals(item.getName(), result.get(0).name());
        Assertions.assertEquals(item.getColumnValues().get(0).getText(), result.get(0).estimation());
        Assertions.assertEquals(item.getColumnValues().get(1).getText(), result.get(0).status());
    }
}