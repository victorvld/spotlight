package os.spotlight.link.adapter.monday.graph.ql.api.mapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import os.spotlight.link.adapter.monday.graph.ql.api.test.data.factory.MondayResponsesOm;

class MondayMapperTest {

    @Test
    void convertToBoards() {
        var getAllBoardssResponse = MondayResponsesOm.Boards.complete().build();

        var result = MondayMapper.of(getAllBoardssResponse);

        Assertions.assertEquals(getAllBoardssResponse.getData().getBoards().get(0).getId(), result.get(0).boardId());
        Assertions.assertEquals(getAllBoardssResponse.getData().getBoards().get(0).getName(), result.get(0).boardName());
    }

    @Test
    void convertToGroups() {
        var getAllGroupsResponse = MondayResponsesOm.Groups.complete().build();

        var result = MondayMapper.of(getAllGroupsResponse);

        Assertions.assertEquals(getAllGroupsResponse.getData().getBoards().get(0).getGroups().get(0).getId(), result.get(0).groupId());
        Assertions.assertEquals(getAllGroupsResponse.getData().getBoards().get(0).getGroups().get(0).getTitle(), result.get(0).groupName());
    }
}