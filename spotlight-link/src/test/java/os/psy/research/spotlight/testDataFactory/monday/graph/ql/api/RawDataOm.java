package os.psy.research.spotlight.testDataFactory.monday.graph.ql.api;

import os.psy.research.spotlight.domain.adpater.monday.graph.ql.api.mapper.RawBoards;

import java.util.List;

public class RawDataOm {

    public static class Boards {
        public static RawBoards.RawBoardsBuilder complete() {
            return RawBoards.builder()
                    .data(RawBoards.Data.builder().boards(
                            List.of(Board.complete().build())
                    ).build());
        }
    }

    public static class Board {
        public static RawBoards.Data.Board.BoardBuilder complete() {
            return RawBoards.Data.Board.builder().name("name").id("1.0");
        }
    }

}
