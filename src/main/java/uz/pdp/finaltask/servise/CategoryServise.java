package uz.pdp.finaltask.servise;

import org.hibernate.internal.ExceptionMapperStandardImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.finaltask.entity.Category;
import uz.pdp.finaltask.mapper.Mappers;
import uz.pdp.finaltask.payload.ApiResponce;
import uz.pdp.finaltask.payload.CategoryDto;
import uz.pdp.finaltask.repo.CategoryRepo;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServise {
    @Autowired
    CategoryRepo categoryRepo;




    public ApiResponce add(CategoryDto categoryDto) {

        if (categoryDto.getId() == null) {

            if (categoryDto.getParentId() != null) {
                Optional<Category> otaCategory = categoryRepo.findById(categoryDto.getParentId());
                if (otaCategory.isEmpty())
                    return new ApiResponce(false, " ota category topilmadi");

                categoryRepo.save(new Category(categoryDto.getName(), otaCategory.get(), true));
                return new ApiResponce(true, "Added sub category");
            }
            categoryRepo.save(new Category(categoryDto.getName(), null, true));
            return new ApiResponce(true, "Added");

        }
        Optional<Category> optionalCategory = categoryRepo.findById(categoryDto.getId());
        final Optional<Category> optionalParentCategory = categoryRepo.findById(categoryDto.getParentId());
        if (optionalCategory.isEmpty())
            return new ApiResponce(false, "topilmadi");

        if (optionalParentCategory.isEmpty())
            return new ApiResponce(false, "ota category topilmadi");


        Category category = optionalCategory.get();
        category.setName(categoryDto.getName());
        category.setCategory(optionalParentCategory.get());
        categoryRepo.save(category);
        return new ApiResponce(false, "Moffaqiyatli qo'sjildi");
    }

    public ApiResponce delete(Long id) {
        try {
            categoryRepo.deleteById(id);
        } catch (Exception e) {
            return new ApiResponce(false, "Not deleted");

        }
        return new ApiResponce(false, "Deleted");

    }

    public ApiResponce get(Long parentId) {

        List<CategoryDto> collect = categoryRepo.findByCategoryId(parentId)
                .stream()
                .map(this::GenerateCategoryDto)
                .collect(Collectors.toList());

        return new ApiResponce(true,"ok",collect);
    }

    public CategoryDto GenerateCategoryDto(Category category){
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setName(category.getName());
        if (category.getCategory()!=null){
            
        categoryDto.setParentId(category.getCategory().getId());
        }
        return categoryDto;
    }

}
