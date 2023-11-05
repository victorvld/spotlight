package os.psy.research.spotlight.domain.adpater.monday.graph.ql.api.mapper;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;


@Builder
public class RawBoards {
    @JsonProperty("data")
    public Data getData() {
        return this.data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    Data data;

    @JsonProperty("account_id")
    public int getAccount_id() {
        return this.account_id;
    }

    public void setAccount_id(int account_id) {
        this.account_id = account_id;
    }

    int account_id;

    @Builder
    @Getter
    public static class Data {

        @JsonProperty("boards")
        public List<Board> getBoards() {
            return this.boards;
        }

        public void setBoards(ArrayList<Board> boards) {
            this.boards = boards;
        }

        public List<Board> boards;

        @Builder
        @Getter
        public static class Board {


            @JsonProperty("name")
            public String getName() {
                return this.name;
            }

            public void setName(String name) {
                this.name = name;
            }

            String name;

            @JsonProperty("id")
            public String getId() {
                return this.id;
            }

            public void setId(String id) {
                this.id = id;
            }

            String id;

        }

    }
}




