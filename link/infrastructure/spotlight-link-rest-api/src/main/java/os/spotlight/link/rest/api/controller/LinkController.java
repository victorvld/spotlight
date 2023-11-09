package os.spotlight.link.rest.api.controller;

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
import os.psy.research.spotlight.domain.service.LinkService;
import os.spotlight.link.rest.api.mapper.BoardMapper;
import os.spotlight.link.rest.api.dto.BoardDto;
import os.spotlight.link.rest.api.dto.GetBoardsRequest;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "${spotlight.api.link.path}")
public class
LinkController {

    private final LinkService service;

    private final BoardMapper mapper;

    @GetMapping(value = "/boards", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    ResponseEntity<List<BoardDto>> getAllBoards(@Valid @RequestBody GetBoardsRequest request) {
        var boards = service.getAllBoardsForGivenAccount(request.accountId());
        return new ResponseEntity<>(mapper.toDto(boards), HttpStatus.OK);
    }
}
