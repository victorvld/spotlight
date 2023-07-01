package os.psy.research.spotlight.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.Duration;
import java.time.OffsetDateTime;

@Getter
@Builder
@AllArgsConstructor
public class Interruption {

    private final String type;
    private final String reasonType;
    private final OffsetDateTime recordedAt;
    private final Duration duration;

}
