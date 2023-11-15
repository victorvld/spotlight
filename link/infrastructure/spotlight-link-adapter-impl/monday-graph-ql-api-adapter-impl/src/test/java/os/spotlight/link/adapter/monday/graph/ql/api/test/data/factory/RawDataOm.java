package os.spotlight.link.adapter.monday.graph.ql.api.test.data.factory;


import monday.graph.ql.api.Board;
import monday.graph.ql.api.Data;
import monday.graph.ql.api.GetAllGroupsResponse;
import monday.graph.ql.api.Group;
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

    public static class Groups {
        public static GetAllGroupsResponse complete() {
            var groups = List.of(Group.builder().withId("groupId").withTitle("title").build());
            var boards = List.of(Board.builder().withGroups(groups).build());
            var data = Data.builder().withBoards(boards).build();
            return GetAllGroupsResponse.builder()
                    .withAccountId(2d)
                    .withData(data)
                    .build();

        }
    }

}
