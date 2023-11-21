package os.spotlight.link.adapter.jira.software.cloud.rest.api.test.data.factory;

import jira.software.cloud.rest.api.boards.GetAllBoardsResponse;
import jira.software.cloud.rest.api.boards.JiraBoard;
import jira.software.cloud.rest.api.issues.GetAllItemsResponse;
import jira.software.cloud.rest.api.issues.JiraIssue;
import jira.software.cloud.rest.api.issues.JiraIssueFields;
import jira.software.cloud.rest.api.issues.Status__1;
import jira.software.cloud.rest.api.sprints.GetAllGroupsResponse;
import jira.software.cloud.rest.api.sprints.JiraSprint;

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

    public static class Groups {

        public static GetAllGroupsResponse.GetAllGroupsResponseBuilderBase<?> complete() {
            return GetAllGroupsResponse.builder()
                    .withValues(List.of(Group.complete().build()))
                    .withTotal(10.0)
                    .withIsLast(true)
                    .withMaxResults(100.0)
                    .withStartAt(0.0);

        }


    }
    public static class Items {

        public static GetAllItemsResponse.GetAllItemsResponseBuilderBase<?> complete() {
            return GetAllItemsResponse.builder()
                    .withIssues(List.of(Issue.complete().build()))
                    .withTotal(10.0)
                    .withExpand("expand")
                    .withMaxResults(100.0)
                    .withStartAt(0.0);

        }

    }
    public static class Board {

        public static JiraBoard.JiraBoardBuilderBase<?> complete() {
            return JiraBoard.builder()
                    .withName("boardName")
                    .withId(1);
        }

    }
    public static class Group {

        public static JiraSprint.JiraSprintBuilderBase<?> complete() {
            return JiraSprint.builder()
                    .withName("groupName")
                    .withId(1);
        }

    }

    public static class Issue {
        public static JiraIssue.JiraIssueBuilderBase<?> complete() {
            var requiredFields = JiraIssueFields.builder()
                    .withCustomfield10016(1)
                    .withSummary("Issue Summary")
                    .withStatus(Status__1.builder().withName("Done").build())
                    .build();
            return JiraIssue.builder()
                    .withId("issueId")
                    .withKey("issueKey")
                    .withFields(requiredFields);
        }

    }
    public static class Location {

        public static jira.software.cloud.rest.api.boards.Location.LocationBuilderBase<?> complete() {
            return jira.software.cloud.rest.api.boards.Location.builder()
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
