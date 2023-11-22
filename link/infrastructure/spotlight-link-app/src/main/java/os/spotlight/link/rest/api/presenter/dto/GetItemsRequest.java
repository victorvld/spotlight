package os.spotlight.link.rest.api.presenter.dto;

import lombok.Builder;

@Builder
public record GetItemsRequest(String accountId, String boardId, String groupId) {
}
