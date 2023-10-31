package os.psy.research.spotlight.domain.adpater.monday.mapper;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.ArrayList;

public class GetAllBoardsMondayApiResponse {
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

    @Getter
    public static class Data {

        @JsonProperty("boards")
        public ArrayList<Board> getBoards() {
            return this.boards;
        }

        public void setBoards(ArrayList<Board> boards) {
            this.boards = boards;
        }

        public ArrayList<Board> boards;

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




