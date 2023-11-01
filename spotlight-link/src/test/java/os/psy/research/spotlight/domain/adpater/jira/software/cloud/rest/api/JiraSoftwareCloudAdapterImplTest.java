package os.psy.research.spotlight.domain.adpater.jira.software.cloud.rest.api;

import org.junit.jupiter.api.Test;
import os.psy.research.spotlight.domain.adpater.schema.SchemaValidator;
import os.psy.research.spotlight.domain.entity.Account;

class JiraSoftwareCloudAdapterImplTest {

    private SchemaValidator validator;

    private JiraSoftwareCloudAdapterImpl underTest = new JiraSoftwareCloudAdapterImpl(validator);

    // TODO: 01/11/2023 Use https://designer.mocky.io to generate the Jira API responses,
    //  documented here: https://developer.atlassian.com/cloud/jira/software/rest/api-group-other-operations/#api-rest-agile-1-0-board-get
    // Offline solution: Postman Mock Serve or JsonServer.
    @Test
    void getAllBoards() {
        var acc = Account.builder().build();
        //underTest.getAllBoards(acc);
    }

}
