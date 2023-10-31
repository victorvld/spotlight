package os.psy.research.spotlight.presentation.controller;

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
import os.psy.research.spotlight.presentation.Mapper.BoardMapper;
import os.psy.research.spotlight.presentation.dto.BoardDto;
import os.psy.research.spotlight.presentation.dto.GetBoardsRequest;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = LinkController.SPOTLIGHT_V_1_LINK_API)
public class LinkController {

    // TODO: 31/10/2023 this constant should be declare in the application properties since it could change.
    public static final String SPOTLIGHT_V_1_LINK_API = "/rest/os.psy.research.spotlight.v1.LinkApi";
    private final LinkService service;
    private final BoardMapper mapper;

    @GetMapping(value = "/boards", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    ResponseEntity<List<BoardDto>> getAllBoards(@Valid @RequestBody GetBoardsRequest request) {
        var boards = service.getAllBoardsForGivenAccount(request.accountId());
        return new ResponseEntity<>(mapper.toDto(boards), HttpStatus.OK);
    }
}
