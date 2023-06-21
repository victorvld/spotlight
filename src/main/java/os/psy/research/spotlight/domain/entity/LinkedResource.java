package os.psy.research.spotlight.domain.entity;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LinkedResource extends AbstractEntity {

    String projectId;

    String taskId;
}
