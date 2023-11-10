package os.spotlight.factory;

import os.spotlight.adapter.ProjectManagerVendorAdapter;

public interface ProjectManagerVendorFactory {
    ProjectManagerVendorAdapter get(String accountId);
}
