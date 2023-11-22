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

    @Test
    void toItems() {
        var getAllItemsResponse = JiraResponsesOm.Items.complete().build();

        var result = JiraMapper.of(getAllItemsResponse);

        Assertions.assertEquals(getAllItemsResponse.getIssues().get(0).getId(), result.get(0).id());
        Assertions.assertEquals("issueKey : Issue Summary", result.get(0).name());
        Assertions.assertEquals(getAllItemsResponse.getIssues().get(0).getFields().getStatus().getName(), result.get(0).status());
        Assertions.assertEquals(getAllItemsResponse.getIssues().get(0).getFields().getCustomfield10016().toString(), result.get(0).estimation());
    }

}
