package os.spotlight.link.adapter.jira.software.cloud.rest.api.converter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import os.spotlight.link.adapter.jira.software.cloud.rest.api.test.data.factory.JiraResponsesOm;

class JiraMapperTest {
    @Test
    void toBoards() {
        var rawBoards = JiraResponsesOm.Boards.complete().build();

        var result = JiraMapper.of(rawBoards);

        Assertions.assertEquals(rawBoards.getValues().get(0).getId().toString(), result.get(0).boardId());
        Assertions.assertEquals(rawBoards.getValues().get(0).getName(), result.get(0).boardName());
    }

}
