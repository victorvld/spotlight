package os.spotlight.persistance.entity;

import lombok.Builder;

@Builder
public record Group(String groupId, String groupName) {
}
