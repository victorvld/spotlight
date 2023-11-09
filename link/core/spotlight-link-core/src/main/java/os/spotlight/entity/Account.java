package os.spotlight.entity;

import lombok.Builder;

@Builder
public record Account(String type, String username, String token, String domain) {
}
