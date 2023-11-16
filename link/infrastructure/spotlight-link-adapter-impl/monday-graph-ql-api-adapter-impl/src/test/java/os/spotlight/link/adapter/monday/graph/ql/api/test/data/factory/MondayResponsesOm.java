package os.spotlight.link.adapter.monday.graph.ql.api.test.data.factory;

import monday.graph.ql.api.boards.GetAllBoardsResponse;
import monday.graph.ql.api.groups.GetAllGroupsResponse;
import monday.graph.ql.api.groups.Group;

import java.util.List;

public class MondayResponsesOm {

    public static class Boards {
        public static GetAllBoardsResponse.GetAllBoardsResponseBuilderBase<?> complete() {
            var boards = List.of(monday.graph.ql.api.boards.Board.builder().withName("boardName").withId("boardId").build());
            var data = monday.graph.ql.api.boards.Data.builder().withBoards(boards).build();
            return GetAllBoardsResponse.builder()
                    .withAccountId(2d)
                    .withData(data);
        }
    }

    public static class Groups {
        public static GetAllGroupsResponse.GetAllGroupsResponseBuilderBase<?> complete() {
            var groups = List.of(Group.builder().withId("groupId").withTitle("title").build());
            var boards = List.of(monday.graph.ql.api.groups.Board.builder().withGroups(groups).build());
            var data = monday.graph.ql.api.groups.Data.builder().withBoards(boards).build();
            return GetAllGroupsResponse.builder()
                    .withAccountId(2d)
                    .withData(data);

        }
    }

}
