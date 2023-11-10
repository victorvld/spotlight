package os.spotlight.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import os.spotlight.entity.Board;
import os.spotlight.factory.ProjectManagerVendorFactory;
import os.spotlight.repository.AccountRepositoryService;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class LinkService {

    private final ProjectManagerVendorFactory projectManagerVendorFactory;

    private final AccountRepositoryService repository;

    public List<Board> getAllBoardsForGivenAccount(String accountId) {
        var account = repository.findByEntityId(accountId);
        return this.projectManagerVendorFactory.get(account.type()).getAllBoards(account);
    }
}
