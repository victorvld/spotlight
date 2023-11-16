package os.spotlight.link.adapter.jira.software.cloud.rest.api.test.data.factory;

import jira.software.cloud.rest.api.GetAllBoardsResponse;
import jira.software.cloud.rest.api.JiraBoard;

import java.util.List;

public class JiraResponsesOm {

    public static class Boards {
        public static GetAllBoardsResponse.GetAllBoardsResponseBuilderBase<?> complete() {
            return GetAllBoardsResponse.builder()
                    .withValues(List.of(Board.complete().build()))
                    .withTotal(10.0)
                    .withIsLast(true)
                    .withMaxResults(100.0)
                    .withStartAt(0.0);
        }

        public static GetAllBoardsResponse.GetAllBoardsResponseBuilderBase<?> empty() {
            return GetAllBoardsResponse.builder();
        }
    }

    public static class Board {
        public static JiraBoard.JiraBoardBuilderBase<?> complete() {
            return JiraBoard.builder()
                    .withName("name")
                    .withId(1);
        }
    }

    public static class Location {
        public static jira.software.cloud.rest.api.Location.LocationBuilderBase<?> complete() {
            return jira.software.cloud.rest.api.Location.builder()
                    .withAvatarURI("Your Avatar URI")
                    .withProjectKey("Your Project Key")
                    .withDisplayName("Your Display Name")
                    .withName("Your Name")
                    .withProjectName("Your Project Name")
                    .withProjectId(123.45)
                    .withProjectTypeKey("Your Project Type Key");
        }
    }

}
