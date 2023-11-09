package os.spotlight.service;

import os.spotlight.entity.Account;
import os.spotlight.entity.Board;

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
