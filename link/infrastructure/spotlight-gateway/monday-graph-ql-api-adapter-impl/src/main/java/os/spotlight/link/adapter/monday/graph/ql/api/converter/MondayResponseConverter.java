package os.spotlight.link.adapter.monday.graph.ql.api.converter;

import os.spotlight.persistance.entity.Board;
import os.spotlight.link.adapter.monday.graph.ql.api.mapper.GetAllBoardsResponse;

import java.util.List;

public class MondayResponseConverter {

    private MondayResponseConverter() {
    }

    public static List<Board> convertToBoards(GetAllBoardsResponse response) {
        return response.getData().getBoards().stream().map(MondayResponseConverter::convertToBoards)
                .toList();
    }

    private static Board convertToBoards(GetAllBoardsResponse.Data.Board response) {
        return Board.builder()
                .boardId(String.valueOf(response.getId()))
                .boardName(response.getName())
                .build();
    }

}
