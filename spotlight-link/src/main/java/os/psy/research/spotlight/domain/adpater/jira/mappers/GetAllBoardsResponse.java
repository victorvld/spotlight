package os.psy.research.spotlight.domain.adpater.jira.mappers;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
public class GetAllBoardsResponse {

    private int total;
    private boolean isLast;
    private int maxResults;
    private List<Board> values;
    private int startAt;

    @Getter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Board {
        private String name;
        private String self;
        private Location location;
        private int id;
        private String type;

    }
    @Getter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Location {
        @JsonProperty("avatarURI")
        private String avatarURI;
        @JsonProperty("projectName")
        private String projectName;
        @JsonProperty("projectKey")
        private String projectKey;
    }
}
