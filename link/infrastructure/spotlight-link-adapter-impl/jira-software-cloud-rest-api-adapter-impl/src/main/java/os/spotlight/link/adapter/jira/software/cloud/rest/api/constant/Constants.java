package os.spotlight.link.adapter.jira.software.cloud.rest.api.constant;

public final class Constants {
    private Constants() {
    }

    private static final String GET_ALL_BOARDS_URL = "https://%s/rest/agile/1.0/board";

    private static final String GET_ALL_SPRINTS_URL = "https://%s/rest/agile/1.0/board/%s/sprint";

    private static final String GET_ALL_ITEMS_FOR_SPRINT_URL = "https://%s/rest/agile/1.0/board/%s/sprint/%s/issue";

    private static final String GET_ALL_BOARDS_SCHEMA_CLASSPATH = "schemas/jira/software/cloud/rest/api/boards/get_all_boards_jira_api_response_schema.json";

    private static final String GET_ALL_GROUPS_SCHEMA_CLASSPATH = "schemas/jira/software/cloud/rest/api/sprints/get_all_groups_jira_api_response_schema.json";

    public static String getAllBoardsUrl(String domain) {
        return String.format(GET_ALL_BOARDS_URL, domain);
    }

    public static String getAllGroupsUrl(String domain, Integer boardId) {
        return String.format(GET_ALL_SPRINTS_URL, domain, boardId);
    }

    public static String getAllItemsUrl(String domain, Integer boardId, Integer sprintId) {
        return String.format(GET_ALL_ITEMS_FOR_SPRINT_URL, domain, boardId, sprintId);
    }

    public static String getAllBoardsSchemaClasspath() {
        return GET_ALL_BOARDS_SCHEMA_CLASSPATH;
    }

    public static String getAllGroupsSchemaClasspath() {
        return GET_ALL_GROUPS_SCHEMA_CLASSPATH;
    }

}
