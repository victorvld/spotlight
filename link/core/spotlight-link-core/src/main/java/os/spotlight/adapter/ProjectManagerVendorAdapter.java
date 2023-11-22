package os.spotlight.adapter;

import os.spotlight.persistance.entity.Account;
import os.spotlight.persistance.entity.Board;
import os.spotlight.persistance.entity.Group;
import os.spotlight.persistance.entity.Item;

import java.util.List;

public interface ProjectManagerVendorAdapter {
    List<Board> getAllBoards(Account account);

    List<Group> getAllGroups(Account account, String boardId);

    List<Item> getAllItems(Account account, String boardId, String groupId);

}

