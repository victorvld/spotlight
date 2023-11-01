package os.psy.research.spotlight.domain.adpater.jira.software.cloud.rest.api.constant;

public final class JiraSoftwareCloudAdapterImplConstants {
    private JiraSoftwareCloudAdapterImplConstants() {
    }

    private static final String GET_ALL_BOARDS_URL = "https://%s/rest/agile/1.0/board";

    private static final String JIRA_SOFTWARE_CLOUD_REST_API_GET_ALL_BOARDS_SCHEMA_CLASSPATH = "schemas/jira/software/cloud/rest/api/get_all_boards_jira_api_response_schema.json";

    public static String getAllBoardsUrl() {
        return GET_ALL_BOARDS_URL;
    }

    public static String getAllBoardsSchemaClasspath() {
        return JIRA_SOFTWARE_CLOUD_REST_API_GET_ALL_BOARDS_SCHEMA_CLASSPATH;
    }

}