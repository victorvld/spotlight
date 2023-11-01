package os.psy.research.spotlight.domain.adpater.jira.software.cloud.rest.api.converter;

import jira.software.cloud.rest.api.RawBoards;
import org.junit.jupiter.api.Test;
class BoardsConverterTest {

    @Test
    void toBoards() {
        RawBoards raw = new RawBoards();
        BoardsConverter.of(raw);
    }

}
