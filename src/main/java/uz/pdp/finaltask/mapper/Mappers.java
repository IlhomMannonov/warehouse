package uz.pdp.finaltask.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.stereotype.Component;
import uz.pdp.finaltask.entity.Category;
import uz.pdp.finaltask.payload.CategoryDto;

@Mapper(componentModel = "spring")
@Component
public interface Mappers {

    @Mapping(target = "parentId", source = "category")
    CategoryDto categoryToCategortDto(Category category);
}
