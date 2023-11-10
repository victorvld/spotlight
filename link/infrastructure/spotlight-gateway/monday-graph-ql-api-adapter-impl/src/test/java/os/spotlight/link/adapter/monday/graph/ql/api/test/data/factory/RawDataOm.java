package os.spotlight.link.adapter.monday.graph.ql.api.test.data.factory;


import os.spotlight.link.adapter.monday.graph.ql.api.mapper.GetAllBoardsResponse;

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
