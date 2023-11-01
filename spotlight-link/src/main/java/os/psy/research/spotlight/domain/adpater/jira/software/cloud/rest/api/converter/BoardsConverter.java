package os.psy.research.spotlight.domain.adpater.jira.software.cloud.rest.api.converter;

import jira.software.cloud.rest.api.RawBoard;
import jira.software.cloud.rest.api.RawBoards;
import os.psy.research.spotlight.domain.entity.Board;

import java.util.Collections;
import java.util.List;

public class BoardsConverter {
    private BoardsConverter() {
    }

    public static List<Board> of(RawBoards raw) {
        if (raw == null || raw.getValues() == null)
            return Collections.emptyList();
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
