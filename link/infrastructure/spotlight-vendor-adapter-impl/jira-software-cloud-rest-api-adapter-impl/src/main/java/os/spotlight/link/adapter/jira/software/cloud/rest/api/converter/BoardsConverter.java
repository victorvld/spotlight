package os.spotlight.link.adapter.jira.software.cloud.rest.api.converter;

import jira.software.cloud.rest.api.RawBoard;
import jira.software.cloud.rest.api.RawBoards;
import os.spotlight.entity.Board;

import java.util.List;

public class BoardsConverter {
    private BoardsConverter() {
    }

    public static List<Board> of(RawBoards raw) {
        return raw.getValues().stream()
                .map(BoardsConverter::of)
                .toList();
    }

    private static Board of(RawBoard raw) {
        return Board.builder()
                .boardId(String.valueOf(raw.getId()))
                .boardName(raw.getName())
                .build();
    }
}
