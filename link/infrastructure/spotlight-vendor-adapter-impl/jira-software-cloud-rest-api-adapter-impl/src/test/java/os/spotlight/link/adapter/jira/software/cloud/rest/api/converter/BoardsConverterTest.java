package os.spotlight.link.adapter.jira.software.cloud.rest.api.converter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import os.spotlight.link.adapter.jira.software.cloud.rest.api.test.data.factory.RawDataOm;

class BoardsConverterTest {
    @Test
    void toBoards() {
        var rawBoards = RawDataOm.Boards.complete().build();

        var result = BoardsConverter.of(rawBoards);

        Assertions.assertEquals(rawBoards.getValues().get(0).getId().toString(), result.get(0).boardId());
        Assertions.assertEquals(rawBoards.getValues().get(0).getName(), result.get(0).boardName());
    }

}
