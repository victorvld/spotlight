package os.spotlight.link.rest.api.test.data.factory;

import os.spotlight.persistance.entity.Board;
public class EntityOm {

    public static Board.BoardBuilder complete() {
        return Board.builder()
                .boardId("boardId")
                .boardName("boardName");
    }
}
