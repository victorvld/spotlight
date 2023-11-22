package os.spotlight.link.adapter.monday.graph.ql.api.test.data.factory;

import monday.graph.ql.api.boards.GetAllBoardsResponse;
import monday.graph.ql.api.groups.GetAllGroupsResponse;
import monday.graph.ql.api.groups.Group;
import monday.graph.ql.api.items.ColumnValue;
import monday.graph.ql.api.items.GetAllItemsResponse;
import monday.graph.ql.api.items.Item;

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

    public static class Items {
        public static GetAllItemsResponse.GetAllItemsResponseBuilderBase<?> complete() {
            var estimationCol = ColumnValue.builder().withId("task_estimation").withTitle("Estimation SP").withText("5").build();
            var statusCol = ColumnValue.builder().withId("task_status").withTitle("Status").withText("Done").build();
            var items = List.of(Item.builder().withId("itemId").withName("itemName").withColumnValues(List.of(estimationCol, statusCol)).build());
            var groups = List.of(monday.graph.ql.api.items.Group.builder().withItems(items).build());
            var boards = List.of(monday.graph.ql.api.items.Board.builder().withGroups(groups).build());
            var data = monday.graph.ql.api.items.Data.builder().withBoards(boards).build();
            return GetAllItemsResponse.builder()
                    .withAccountId(2d)
                    .withData(data);
        }
    }
}
