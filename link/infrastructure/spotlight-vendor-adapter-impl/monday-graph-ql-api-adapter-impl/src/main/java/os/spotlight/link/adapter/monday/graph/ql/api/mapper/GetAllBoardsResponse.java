package os.spotlight.link.adapter.monday.graph.ql.api.mapper;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.validation.Valid;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "data",
        "account_id"
})
public class GetAllBoardsResponse {

    @JsonProperty("data")
    @Valid
    private Data data;

    @JsonProperty("account_id")
    private Integer accountId;

    public GetAllBoardsResponse withData(Data data) {
        this.data = data;
        return this;
    }

    public GetAllBoardsResponse withAccountId(Integer accountId) {
        this.accountId = accountId;
        return this;
    }

    public Data getData() {
        return this.data;
    }

    public Integer getAccountId() {
        return accountId;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({"boards"})
    public static class Data {

        @JsonProperty("boards")
        @Valid
        private List<Board> boards;

        public Data withBoards(List<Board> boards) {
            this.boards = boards;
            return this;
        }

        public List<Board> getBoards() {
            return boards;
        }

        public static class Board {

            @JsonProperty("name")
            private String name;
            @JsonProperty("id")
            private String id;

            public Board withName(String name) {
                this.name = name;
                return this;
            }

            public Board withId(String id) {
                this.id = id;
                return this;
            }

            public String getName() {
                return name;
            }

            public String getId() {
                return id;
            }
        }

    }

}

