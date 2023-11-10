package os.spotlight.adapter;

import os.spotlight.entity.Account;
import os.spotlight.entity.Board;

import java.util.List;

public interface ProjectManagerVendorAdapter {
    List<Board> getAllBoards(Account account);

//    Set<Group> getAllGroups(String boardId);
//
//    Set<Items> getAllItems();
//
//    Set<Items> getAllItemsByBoardId(String boardId);
//
//    Set<Items> getAllItemsByGroupId(String boardId, String groupId);
}
