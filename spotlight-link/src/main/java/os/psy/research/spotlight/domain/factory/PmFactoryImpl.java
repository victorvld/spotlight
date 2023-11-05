package os.psy.research.spotlight.domain.factory;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import os.psy.research.spotlight.domain.adpater.client.HttpClient;
import os.psy.research.spotlight.domain.adpater.client.impl.OkHttpClient;
import os.psy.research.spotlight.domain.adpater.jira.software.cloud.rest.api.JiraSoftwareCloudAdapterImpl;
import os.psy.research.spotlight.domain.adpater.monday.graph.ql.api.MondayGraphQlAdapterImpl;
import os.psy.research.spotlight.domain.adpater.processor.JsonDeserializer;
import os.psy.research.spotlight.domain.adpater.processor.JsonProcessor;
import os.psy.research.spotlight.domain.adpater.processor.JsonValidator;
import os.psy.research.spotlight.domain.adpater.response.handling.strategy.ResponseHandlingStrategy;
import os.psy.research.spotlight.domain.adpater.response.handling.strategy.impl.ThrowExceptionStrategy;
import os.psy.research.spotlight.domain.service.PmAdapter;
import os.psy.research.spotlight.domain.service.PmFactory;
import os.psy.research.spotlight.infrastructure.errorhandling.exceptions.UnknownPmVendor;

@Component
public class PmFactoryImpl implements PmFactory {

    // TODO: 01/11/2023 Think if these fields should be singletons.
    // TODO: 03/11/2023 Think if these fields have to be injected based on app config.
    private final ObjectMapper mapper = new ObjectMapper();
    private final JsonValidator validator = new JsonValidator(mapper);
    private final JsonDeserializer deserializer = new JsonDeserializer(mapper);
    private final JsonProcessor processor = new JsonProcessor(validator, deserializer);
    private final HttpClient client = new OkHttpClient();
    private final ResponseHandlingStrategy resHandlingStrategy = new ThrowExceptionStrategy();

    @Override
    public PmAdapter get(String vendor) {
        return switch (vendor) {
            case "jira" -> new JiraSoftwareCloudAdapterImpl(processor, client, resHandlingStrategy);
            case "monday" -> new MondayGraphQlAdapterImpl(client, resHandlingStrategy, deserializer);
            default -> throw new UnknownPmVendor(String.format("Unknown Project Manager Vendor %s.", vendor));
        };
    }
}
