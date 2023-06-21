package os.psy.research.spotlight.testDataFactory;

import os.psy.research.spotlight.domain.entity.FocusUnit;
import os.psy.research.spotlight.domain.entity.LinkedResource;

public class FocusUnitMother {

    public static FocusUnit.FocusUnitBuilder complete() {
        return FocusUnit.builder()
                .userId("userId")
                .linkedResource(LinkedResource.builder()
                        .projectId("projectId")
                        .taskId("taskId")
                        .build());
    }
}
