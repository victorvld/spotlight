package os.spotlight.link.rest.api.factory.impl;

import lombok.RequiredArgsConstructor;
import os.spotlight.adapter.ProjectManagerVendorAdapter;
import os.spotlight.factory.ProjectManagerVendorFactory;
import os.spotlight.link.factory.adpater.gateway.JiraGateway;
import os.spotlight.link.factory.adpater.gateway.MondayGateway;
import os.spotlight.link.rest.api.factory.impl.exception.UnknownPmVendor;

@RequiredArgsConstructor
public class ProjectManagerVendorFactoryImpl implements ProjectManagerVendorFactory {
//    private final JsonProcessor processor;
//    private final JsonDeserializer deserializer;
//    private final HttpClient client;
//    private final ResponseHandlingStrategy strategy;
    private final JiraGateway jiraGateway;
    private final MondayGateway mondayGateway;

    @Override
    public ProjectManagerVendorAdapter get(String vendor) {
        return switch (vendor) {
//            case "jira" -> new JiraSoftwareCloudAdapterImpl(processor, client, strategy);
//            case "monday" -> new MondayGraphQlAdapterImpl(client, strategy, deserializer);
            case "jira" -> jiraGateway;
            case "monday" -> mondayGateway;
            default -> throw new UnknownPmVendor(String.format("Unknown Project Manager Vendor %s.", vendor));
        };
    }
}
