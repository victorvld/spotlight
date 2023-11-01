package os.psy.research.spotlight.presentation.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Named;
import os.psy.research.spotlight.domain.entity.Board;
import os.psy.research.spotlight.presentation.dto.BoardDto;

import java.util.List;


@Mapper(componentModel = "spring")
public interface BoardMapper {
    @Named("toBoardDto")
    BoardDto toDto(Board board);

    @Named("toBoardsDto")
    List<BoardDto> toDto(List<Board> boards);
}
