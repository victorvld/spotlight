package os.psy.research.spotlight.testDataFactory;

import jira.software.cloud.rest.api.RawBoard;
import jira.software.cloud.rest.api.RawBoards;

import java.util.List;

public class RawDataOm {

    public static class Boards {
        public static RawBoards.RawBoardsBuilderBase<?> complete() {
            return RawBoards.builder()
                    .withValues(List.of(Board.complete().build()))
                    .withTotal(10.0)
                    .withIsLast(true)
                    .withMaxResults(100.0)
                    .withStartAt(0.0);
        }

        public static RawBoards.RawBoardsBuilderBase<?> empty() {
            return RawBoards.builder();
        }
    }

    public static class Board {
        public static RawBoard.RawBoardBuilderBase<?> complete() {
            return jira.software.cloud.rest.api.RawBoard.builder()
                    .withName("name")
                    .withId(1d);
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
