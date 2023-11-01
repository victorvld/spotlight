package os.psy.research.spotlight.testDataFactory;

import os.psy.research.spotlight.domain.entity.Board;

public class EntityObjectMother {

    public static Board.BoardBuilder complete() {
        return Board.builder()
                .boardId("boardId")
                .boardName("boardName");
    }
}
