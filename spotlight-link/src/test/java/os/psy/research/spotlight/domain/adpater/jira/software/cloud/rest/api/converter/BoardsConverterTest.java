package os.psy.research.spotlight.domain.adpater.jira.software.cloud.rest.api.converter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import os.psy.research.spotlight.testDataFactory.jira.software.cloud.rest.api.RawDataOm;
class BoardsConverterTest {
    @Test
    void toBoards() {
        var rawBoards = RawDataOm.Boards.complete().build();

        var result = BoardsConverter.of(rawBoards);

        Assertions.assertEquals(rawBoards.getValues().get(0).getId().toString(), result.get(0).getBoardId());
        Assertions.assertEquals(rawBoards.getValues().get(0).getName(), result.get(0).getBoardName());
    }

}
