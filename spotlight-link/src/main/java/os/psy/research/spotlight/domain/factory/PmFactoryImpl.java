package os.psy.research.spotlight.domain.factory;

import org.springframework.stereotype.Component;
import os.psy.research.spotlight.domain.adpater.jira.JiraApi;
import os.psy.research.spotlight.domain.adpater.monday.MondayApi;
import os.psy.research.spotlight.domain.service.PmFactory;
import os.psy.research.spotlight.domain.service.PmAdapter;
import os.psy.research.spotlight.infrastructure.errorHandling.exceptions.UnknownPmVendor;

@Component
public class PmFactoryImpl implements PmFactory {
    @Override
    public PmAdapter get(String vendor) {
        return switch (vendor) {
            case "jira" -> new JiraApi();
            case "monday" -> new MondayApi();
            default -> throw new UnknownPmVendor(String.format("Unknown Project Manager Vendor %s.", vendor));
        };
    }
}
