package os.spotlight.link.adapter.monday.graph.ql.api.constant;

public class Constants {

    private Constants() {
    }


    private static final String API_MONDAY_V2 = "https://api.monday.com/v2/";
    private static final String QUERY_GET_ALL_BOARDS = "query{"
            + "boards(){"
            + "name id}}";

    private static final String QUERY_GET_ALL_GROUPS = "query {"
            + "boards (ids: %s) {"
            + "groups {"
            + "title id}}}";
    private static String QUERY_GET_ALL_ITEMS = "query {"
            + " boards (ids: %s) {"
            + " groups(ids: %s){"
            + " title items {"
            + " id, name, column_values(ids: [task_estimation, task_status]) {"
            + " id, title, text} }}}}";


    public static String getApiMondayV2() {
        return API_MONDAY_V2;
    }

    public static String getQueryGetAllBoards() {
        return QUERY_GET_ALL_BOARDS;
    }


    public static String getQueryGetAllGroups(String boardId) {
        return String.format(QUERY_GET_ALL_GROUPS, boardId);
    }

    public static String getQueryGetAllItems(String boardId, String groupId) {
        return String.format(QUERY_GET_ALL_ITEMS, boardId, groupId);
    }
}
