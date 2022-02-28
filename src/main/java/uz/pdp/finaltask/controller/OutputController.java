package uz.pdp.finaltask.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.finaltask.exeption.PageSizeExeption;
import uz.pdp.finaltask.payload.ApiResponce;
import uz.pdp.finaltask.payload.OutputDto;
import uz.pdp.finaltask.servise.OutputService;
import uz.pdp.finaltask.utils.AppConstants;



@RestController
@RequestMapping("/output")
public class OutputController {
    @Autowired
    OutputService outputService;

    @PostMapping("/add")
    public HttpEntity<?> add(@RequestBody OutputDto outputDto) {
        ApiResponce apiResponce = outputService.add(outputDto);
        return ResponseEntity.ok(apiResponce);
    }

    @GetMapping("/get")
    public HttpEntity<?>getAll(
            @RequestParam(value = "outputId", required = false) long id,
            @RequestParam(value = "page", required = false, defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
            @RequestParam(value = "size", required = false, defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size,
            @RequestParam(value = "all") boolean all) throws PageSizeExeption {
        ApiResponce apiResponce = outputService.getAllPageable(id,page,size,all);
        return ResponseEntity.ok(apiResponce);
    }

    @DeleteMapping("/delete/{id}")
    public HttpEntity<?>delete (@PathVariable Long id){
        ApiResponce apiResponce = outputService.delete(id);
        return ResponseEntity.ok(apiResponce);
    }
}
