package os.spotlight.link.rest.api.presenter.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Named;
import os.spotlight.persistance.entity.Board;
import os.spotlight.link.rest.api.presenter.dto.BoardDto;

import java.util.List;


@Mapper(componentModel = "spring")
public interface BoardMapper {
    @Named("toBoardDto")
    BoardDto toDto(Board board);

    @Named("toBoardsDto")
    List<BoardDto> toDto(List<Board> boards);
}
