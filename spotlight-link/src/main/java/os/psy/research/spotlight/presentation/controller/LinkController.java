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
import os.psy.research.spotlight.presentation.dto.BoardDto;
import os.psy.research.spotlight.presentation.dto.GetBoardsRequest;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/rest/os.psy.research.spotlight.v1.LinkApi")
public class LinkController {

    private final LinkService service;

    @GetMapping(value = "/boards", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    ResponseEntity<List<BoardDto>> getAllBoards(@Valid @RequestBody GetBoardsRequest request) {
        return new ResponseEntity<>(service.getAllBoardsForGivenAccount(request.accountId()), HttpStatus.OK);
    }
}
