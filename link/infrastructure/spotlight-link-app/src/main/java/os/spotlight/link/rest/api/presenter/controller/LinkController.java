package os.spotlight.link.rest.api.presenter.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import os.spotlight.link.rest.api.presenter.dto.GetGroupsRequest;
import os.spotlight.link.rest.api.presenter.mapper.BoardMapper;
import os.spotlight.link.rest.api.presenter.dto.BoardDto;
import os.spotlight.link.rest.api.presenter.dto.GetBoardsRequest;
import os.spotlight.link.rest.api.presenter.mapper.GroupMapper;
import os.spotlight.persistance.entity.LinkService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "${spotlight.api.link.path}")
public class
LinkController {

    private final LinkService service;

    private final BoardMapper boardMapper;

    private final GroupMapper groupMapper;

    @GetMapping(value = "/boards", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    ResponseEntity<List<BoardDto>> getAllBoards(@Valid @RequestBody GetBoardsRequest request) {
        var boards = service.getAllBoardsForGivenAccount(request.accountId());
        return new ResponseEntity<>(boardMapper.toDto(boards), HttpStatus.OK);
    }

    @GetMapping(value = "/groups", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    ResponseEntity<?> getAllGroups(@Valid @RequestBody GetGroupsRequest request) {
        var groups = service.getAllGroupsForGivenAccountAndBoardId(request.accountId(), request.boardId());
        return new ResponseEntity<>(groupMapper.toDto(groups), HttpStatus.OK);
    }
}
