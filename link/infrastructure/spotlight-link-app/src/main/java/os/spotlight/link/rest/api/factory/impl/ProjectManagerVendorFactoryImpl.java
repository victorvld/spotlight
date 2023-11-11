package os.spotlight.link.rest.api.factory.impl;

import lombok.RequiredArgsConstructor;
import os.spotlight.adapter.ProjectManagerVendorAdapter;
import os.spotlight.factory.ProjectManagerVendorFactory;
import os.spotlight.adapter.JiraGateway;
import os.spotlight.adapter.MondayGateway;
import os.spotlight.link.rest.api.factory.impl.exception.UnknownPmVendor;

@RequiredArgsConstructor
public class ProjectManagerVendorFactoryImpl implements ProjectManagerVendorFactory {
    private final JiraGateway jiraGateway;
    private final MondayGateway mondayGateway;

    @Override
    public ProjectManagerVendorAdapter get(String vendor) {
        return switch (vendor) {
            case "jira" -> jiraGateway;
            case "monday" -> mondayGateway;
            default -> throw new UnknownPmVendor(String.format("Unknown Project Manager Vendor %s.", vendor));
        };
    }
}
