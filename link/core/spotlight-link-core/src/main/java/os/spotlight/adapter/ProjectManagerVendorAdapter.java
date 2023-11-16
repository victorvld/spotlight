package os.spotlight.adapter;

import os.spotlight.persistance.entity.Account;
import os.spotlight.persistance.entity.Board;
import os.spotlight.persistance.entity.Group;

import java.util.List;

public interface ProjectManagerVendorAdapter {
    List<Board> getAllBoards(Account account);

    List<Group> getAllGroups(Account capture, String boardId);

//    Set<Group> getAllGroups(String boardId);
//
//    Set<Items> getAllItems();
//
//    Set<Items> getAllItemsByBoardId(String boardId);
//
//    Set<Items> getAllItemsByGroupId(String boardId, String groupId);
}
