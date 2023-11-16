package os.spotlight.link.adapter.monday.graph.ql.api.constant;

public class Constants {
    private Constants() {
    }

    public static final String API_MONDAY_V2 = "https://api.monday.com/v2/";
    public static final String QUERY_GET_ALL_BOARDS_NAME_ID = "query{boards(){name id}}";

    public static final String QUERY_GET_ALL_GROUPS_NAME_ID = "query{boards(){name id}}";


    public static String getApiMondayV2() {
        return API_MONDAY_V2;
    }

    public static String getQueryGetAllBoards() {
        return QUERY_GET_ALL_BOARDS_NAME_ID;
    }


    public static String getQueryGetAllGroups(String boardId) {
        return String.format("query {boards (ids: %s) {groups {title id}}}", boardId);
    }

    public static String getQueryGetAllItems(String boardId, String groupId) {
        return String.format("query { boards (ids: %s) { groups(ids: \"%s\"){ title items {id name state}}}}", boardId, groupId);
    }
}
