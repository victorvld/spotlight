package os.spotlight.persistance.entity;

import lombok.Builder;

@Builder
public record Board(String boardId, String boardName) {
}
