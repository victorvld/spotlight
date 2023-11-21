package os.spotlight.persistance.entity;

import lombok.Builder;

@Builder
public record Item(String name, String id, String status, String estimation, String summary) {
}
