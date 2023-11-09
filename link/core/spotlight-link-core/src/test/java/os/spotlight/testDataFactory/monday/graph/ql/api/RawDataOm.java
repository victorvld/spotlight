package os.spotlight.testDataFactory.monday.graph.ql.api;

import os.spotlight.adpater.monday.graph.ql.api.mapper.GetAllBoardsResponse;

import java.util.List;

public class RawDataOm {

    public static class Boards {
        public static GetAllBoardsResponse complete() {
            var board = new GetAllBoardsResponse.Data.Board().withId("1.0").withName("name");
            var data = new GetAllBoardsResponse.Data().withBoards(List.of(board));
            return new GetAllBoardsResponse().withData(data);

        }

    }

}
