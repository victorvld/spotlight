package os.spotlight.service;

public interface PmFactory {
    PmAdapter get(String accountId);
}
