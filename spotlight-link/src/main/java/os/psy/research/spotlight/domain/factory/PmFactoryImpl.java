package os.psy.research.spotlight.domain.factory;

import org.springframework.stereotype.Component;
import os.psy.research.spotlight.domain.adpater.jira.software.cloud.rest.api.JiraSoftwareCloudAdapterImpl;
import os.psy.research.spotlight.domain.adpater.monday.graph.ql.api.MondayGraphQlAdapterImpl;
import os.psy.research.spotlight.domain.adpater.schema.SchemaValidator;
import os.psy.research.spotlight.domain.service.PmFactory;
import os.psy.research.spotlight.domain.service.PmAdapter;
import os.psy.research.spotlight.infrastructure.errorHandling.exceptions.UnknownPmVendor;

@Component
public class PmFactoryImpl implements PmFactory {

    private final SchemaValidator validator = new SchemaValidator();

    @Override
    public PmAdapter get(String vendor) {
        return switch (vendor) {
            case "jira" -> new JiraSoftwareCloudAdapterImpl(validator);
            case "monday" -> new MondayGraphQlAdapterImpl();
            default -> throw new UnknownPmVendor(String.format("Unknown Project Manager Vendor %s.", vendor));
        };
    }
}
