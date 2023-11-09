package os.psy.research.spotlight.domain.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import os.psy.research.spotlight.domain.entity.Board;
import os.psy.research.spotlight.domain.repository.AccountRepositoryService;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class LinkService {

    private final PmFactory pmFactory;

    private final AccountRepositoryService repository;

    public List<Board> getAllBoardsForGivenAccount(String accountId) {
        var account = repository.findByEntityId(accountId);
        return this.pmFactory.get(account.type()).getAllBoards(account);
    }
}
