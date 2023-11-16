package os.spotlight.persistance.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import os.spotlight.adapter.ProjectManagerVendorAdapter;
import os.spotlight.factory.ProjectManagerVendorFactory;
import os.spotlight.repository.AccountRepositoryService;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class LinkServiceTest {
    private final ProjectManagerVendorFactory mockFactory = Mockito.mock(ProjectManagerVendorFactory.class);
    private final AccountRepositoryService mockRepository = Mockito.mock(AccountRepositoryService.class);
    private final ProjectManagerVendorAdapter mockAdapter = Mockito.mock(ProjectManagerVendorAdapter.class);
    private final LinkService underTest = new LinkService(mockFactory, mockRepository);

    @Test
    void getAllBoardsForGivenAccount() {
        var accountId = "acc";
        var type = "type";
        var account = Account.builder().type(type).build();
        var accountIdCaptor = ArgumentCaptor.forClass(String.class);
        var typeCaptor = ArgumentCaptor.forClass(String.class);
        var accCaptor = ArgumentCaptor.forClass(Account.class);
        when(mockRepository.findByEntityId(accountId)).thenReturn(account);
        when(mockFactory.get(type)).thenReturn(mockAdapter);

        underTest.getAllBoardsForGivenAccount(accountId);

        verify(mockRepository, times(1)).findByEntityId(accountIdCaptor.capture());
        verify(mockFactory, times(1)).get(typeCaptor.capture());
        verify(mockAdapter, times(1)).getAllBoards(accCaptor.capture());
        Assertions.assertEquals(accountId, accountIdCaptor.getValue());
        Assertions.assertEquals(type, typeCaptor.getValue());
        Assertions.assertEquals(account, accCaptor.getValue());
    }

    @Test
    void getAllGroupsForGivenAccountAndBoardId() {
        var boardId = "boardId";
        var accountId = "acc";
        var type = "type";
        var account = Account.builder().type(type).build();
        var accountIdCaptor = ArgumentCaptor.forClass(String.class);
        var typeCaptor = ArgumentCaptor.forClass(String.class);
        var accCaptor = ArgumentCaptor.forClass(Account.class);
        var boardIdCaptor = ArgumentCaptor.forClass(String.class);
        when(mockRepository.findByEntityId(accountId)).thenReturn(account);
        when(mockFactory.get(type)).thenReturn(mockAdapter);

        underTest.getAllGroupsForGivenAccountAndBoardId(accountId, boardId);

        verify(mockRepository, times(1)).findByEntityId(accountIdCaptor.capture());
        verify(mockFactory, times(1)).get(typeCaptor.capture());
        verify(mockAdapter, times(1)).getAllGroups(accCaptor.capture(), boardIdCaptor.capture());
        Assertions.assertEquals(accountId, accountIdCaptor.getValue());
        Assertions.assertEquals(type, typeCaptor.getValue());
        Assertions.assertEquals(account, accCaptor.getValue());
        Assertions.assertEquals(boardId, boardIdCaptor.getValue());
    }
}