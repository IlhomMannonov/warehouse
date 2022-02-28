package uz.pdp.finaltask.utils;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import uz.pdp.finaltask.exeption.PageSizeExeption;


public class CommandUtils {
    public static void validatePage(int page, int size) throws PageSizeExeption {
        if (page < 0) {
            throw new PageSizeExeption("Invalid page number, page must be not less than 0!");
        } else if (size < 0) {
            throw new PageSizeExeption("Invalid page number, page must be not less than 0!");
        } else if (size > AppConstants.MAX_PAGE_SIZE) {
            throw new PageSizeExeption("invalid page size, Page must not be greater than !");
        }
    }


    public static Pageable simplePageable(int page, int size) throws PageSizeExeption {
        validatePage(page, size);
        return PageRequest.of(page, size);
    }

    public static Pageable ascDescPageable(int page, int size, boolean asc) throws PageSizeExeption {
        validatePage(page, size);
        if (asc) {
            return PageRequest.of(page,size,Sort.Direction.ASC,"createdAt");
        }
        return PageRequest.of(page,size,Sort.Direction.DESC,"createdAt");

    }

}
