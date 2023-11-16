package os.spotlight.link.adapter.jira.software.cloud.rest.api.mapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import os.spotlight.link.adapter.jira.software.cloud.rest.api.test.data.factory.JiraResponsesOm;

class JiraMapperTest {
    @Test
    void toBoards() {
        var GetAllBoardsResponse = JiraResponsesOm.Boards.complete().build();

        var result = JiraMapper.of(GetAllBoardsResponse);

        Assertions.assertEquals(GetAllBoardsResponse.getValues().get(0).getId().toString(), result.get(0).boardId());
        Assertions.assertEquals(GetAllBoardsResponse.getValues().get(0).getName(), result.get(0).boardName());
    }

    @Test
    void toGroups() {
        var getAllGroupsResponse = JiraResponsesOm.Groups.complete().build();

        var result = JiraMapper.of(getAllGroupsResponse);

        Assertions.assertEquals(getAllGroupsResponse.getValues().get(0).getId().toString(), result.get(0).groupId());
        Assertions.assertEquals(getAllGroupsResponse.getValues().get(0).getName(), result.get(0).groupName());
    }

}
