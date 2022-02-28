package uz.pdp.finaltask.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.finaltask.exeption.PageSizeExeption;
import uz.pdp.finaltask.payload.ApiResponce;
import uz.pdp.finaltask.payload.InputDto;
import uz.pdp.finaltask.servise.InputService;
import uz.pdp.finaltask.utils.AppConstants;
import uz.pdp.finaltask.utils.CommandUtils;

@RestController
@RequestMapping("/input")
public class InputController {
    @Autowired
    InputService inputService;

    @PostMapping("/add")
    public HttpEntity<?> add(@RequestBody InputDto inputDto) {
        ApiResponce apiResponce = inputService.add(inputDto);
        return ResponseEntity.ok(apiResponce);
    }

    @GetMapping("/getInputProduct")
    public HttpEntity<?> getAll(
            @RequestParam(value = "inputid", required = false) long id,
            @RequestParam(value = "page", required = false, defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
            @RequestParam(value = "size", required = false, defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size,
            @RequestParam(value = "all") boolean all) throws PageSizeExeption {
        ApiResponce apiResponce = inputService.getInputProductByInputId(id, page, size, all);
        return ResponseEntity.ok(apiResponce);
    }



    //delete only inputProduct
    @DeleteMapping("/delete/{id}")
    public HttpEntity<?>delete(@PathVariable Long id){
        ApiResponce apiResponce = inputService.delete(id);
        return ResponseEntity.ok(apiResponce);
    }
}
