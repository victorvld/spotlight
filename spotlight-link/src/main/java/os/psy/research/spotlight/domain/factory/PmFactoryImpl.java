package os.psy.research.spotlight.domain.factory;

import org.springframework.stereotype.Component;
import os.psy.research.spotlight.domain.adpater.jira.JiraApi;
import os.psy.research.spotlight.domain.adpater.MondayApi;
import os.psy.research.spotlight.domain.service.PmFactory;
import os.psy.research.spotlight.domain.service.PmAdapter;

@Component
public class PmFactoryImpl implements PmFactory {
    @Override
    public PmAdapter get(String vendor) {
        return switch (vendor) {
            case "jira" -> new JiraApi();
//            case "monday" -> new MondayApi();
            default -> throw new RuntimeException();
        };
    }
}
