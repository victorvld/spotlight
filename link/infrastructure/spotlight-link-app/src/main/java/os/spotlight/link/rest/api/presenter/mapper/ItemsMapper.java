package os.spotlight.link.rest.api.presenter.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Named;
import os.spotlight.persistance.entity.Item;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ItemsMapper {
    @Named("toItemDto")
    ItemDto toDto(Item item);

    @Named("toItemsDto")
    List<ItemDto> toDto(List<Item> items);
}
