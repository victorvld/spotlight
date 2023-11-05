package os.psy.research.spotlight.domain.adpater.monday.graph.ql.api.constant;

public class Constants {
    private Constants() {
    }

    public static final String API_MONDAY_V2 = "https://api.monday.com/v2/";
    public static final String QUERY_GET_ALL_BOARDS_NAME_ID = "query{boards(){name id}}";

    public static String getApiMondayV2() {
        return QUERY_GET_ALL_BOARDS_NAME_ID;
    }

    public static String getQueryGetAllBoards() {
        return QUERY_GET_ALL_BOARDS_NAME_ID;
    }


}
