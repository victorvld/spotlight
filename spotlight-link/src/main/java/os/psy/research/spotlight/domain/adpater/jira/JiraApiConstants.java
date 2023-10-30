package os.psy.research.spotlight.domain.adpater.jira;

public final class JiraApiConstants {
    private JiraApiConstants() {
    }

    private static final String GET_ALL_BOARDS_URL = "https://%s/rest/agile/1.0/board";

    public static String getAllBoardsUrl() {
        return GET_ALL_BOARDS_URL;
    }
}