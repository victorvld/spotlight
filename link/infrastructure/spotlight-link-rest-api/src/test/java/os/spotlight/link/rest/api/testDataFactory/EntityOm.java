package os.spotlight.link.rest.api.testDataFactory;

import os.psy.research.spotlight.domain.entity.Board;

public class EntityOm {

    public static Board.BoardBuilder complete() {
        return Board.builder()
                .boardId("boardId")
                .boardName("boardName");
    }
}
