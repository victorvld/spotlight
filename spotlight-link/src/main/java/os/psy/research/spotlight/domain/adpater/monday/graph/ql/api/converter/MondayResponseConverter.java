package os.psy.research.spotlight.domain.adpater.monday.graph.ql.api.converter;

import os.psy.research.spotlight.domain.adpater.monday.graph.ql.api.mapper.GetAllBoardsMondayApiResponse;
import os.psy.research.spotlight.domain.entity.Board;

import java.util.Collections;
import java.util.List;

public class MondayResponseConverter {

    private MondayResponseConverter() {
    }

    public static List<Board> convertToBoards(GetAllBoardsMondayApiResponse response) {
        if (response == null || response.getData() == null)
            return Collections.emptyList();
        return response.getData().getBoards().stream()
                .map(MondayResponseConverter::convertToBoards)
                .toList();
    }

    private static Board convertToBoards(GetAllBoardsMondayApiResponse.Data.Board response) {
        return Board.builder()
                .boardId(String.valueOf(response.getId()))
                .boardName(response.getName())
                .build();
    }

}
