package os.psy.research.spotlight.domain.service;

import os.psy.research.spotlight.domain.entity.Account;
import os.psy.research.spotlight.domain.entity.Board;

import java.util.List;

public interface PmAdapter {
    List<Board> getAllBoards(Account account);

//    Set<Group> getAllGroups(String boardId);
//
//    Set<Items> getAllItems();
//
//    Set<Items> getAllItemsByBoardId(String boardId);
//
//    Set<Items> getAllItemsByGroupId(String boardId, String groupId);
}
