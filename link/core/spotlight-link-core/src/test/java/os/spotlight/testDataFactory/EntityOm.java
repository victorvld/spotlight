package os.spotlight.testDataFactory;

import os.spotlight.entity.Board;

public class EntityOm {

    public static Board.BoardBuilder complete() {
        return Board.builder()
                .boardId("boardId")
                .boardName("boardName");
    }
}
