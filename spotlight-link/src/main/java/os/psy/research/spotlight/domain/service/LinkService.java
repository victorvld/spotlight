package os.psy.research.spotlight.domain.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import os.psy.research.spotlight.domain.repository.AccountRepository;
import os.psy.research.spotlight.presentation.Mapper.BoardMapper;
import os.psy.research.spotlight.presentation.dto.BoardDto;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class LinkService {

    private final PmFactory pmFactory;

    private final AccountRepository repository;

    private final BoardMapper mapper;

    public List<BoardDto> getAllBoardsForGivenAccount(String accountId) {
        var account = repository.findByEntityId(accountId);
        return mapper.toDto(this.pmFactory.get(account.getType()).getAllBoards(account));
    }
}
