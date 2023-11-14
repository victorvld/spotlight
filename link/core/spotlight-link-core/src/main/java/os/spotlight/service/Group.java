package os.spotlight.service;

import lombok.Builder;

@Builder
public record Group(String groupId, String groupName) {
}
