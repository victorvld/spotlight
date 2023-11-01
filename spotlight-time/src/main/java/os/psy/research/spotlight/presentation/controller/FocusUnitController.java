package os.psy.research.spotlight.presentation.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import os.psy.research.spotlight.presentation.dto.FocusUnitDto;
import os.psy.research.spotlight.presentation.dto.request.GetFocusUnitsRequest;
import os.psy.research.spotlight.presentation.dto.request.RegisterFocusUnitRequest;
import os.psy.research.spotlight.presentation.mapper.FocusUnitMapper;
import os.psy.research.spotlight.domain.service.FocusUnitService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "${spotlight.api.fu.path}")
public class FocusUnitController {

    private final FocusUnitService service;

    private final FocusUnitMapper mapper;

    @GetMapping(value = "/units", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    ResponseEntity<List<FocusUnitDto>> getFocusUnits(@Valid @RequestBody GetFocusUnitsRequest request) {
        var units = service.getFocusUnits(request.userId());
        return new ResponseEntity<>(units.stream().map(mapper::toDto).toList(), HttpStatus.OK);
    }

    @PostMapping(value = "/unit", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    ResponseEntity<FocusUnitDto> registerFocusUnits(@Valid @RequestBody RegisterFocusUnitRequest request) {
        return new ResponseEntity<>(mapper.toDto(service.registerFocusUnit(mapper.toEntity(request))), HttpStatus.OK);
    }

}
