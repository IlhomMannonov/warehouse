package uz.pdp.finaltask.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.finaltask.exeption.PageSizeExeption;
import uz.pdp.finaltask.payload.ApiResponce;
import uz.pdp.finaltask.payload.ProductDto;
import uz.pdp.finaltask.servise.ProductServise;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductServise productServise;

    @PostMapping("/saveOrEdit")
    public HttpEntity<?> saveOrEdit(@RequestBody ProductDto productDto) {
        ApiResponce apiResponce = productServise.saveOrEdit(productDto);
        return ResponseEntity.status(apiResponce.isSuccess() ? 200 : 409).body(apiResponce);
    }

    @GetMapping("/getAll")
    public HttpEntity<?> getAll(
            @RequestParam(value = "id", required = false) Long categoryId,
            @RequestParam(value = "page", required = false) int page,
            @RequestParam(value = "size", required = false) int size,
            @RequestParam(value = "all", required = false) boolean all) throws PageSizeExeption {
        ApiResponce apiResponce = productServise.getAll(categoryId, page, size, all);
        return ResponseEntity.status(apiResponce.isSuccess() ? 200 : 409).body(apiResponce);
    }

    @DeleteMapping("/delete/]{id}")
    public HttpEntity<?> delete(@PathVariable Long id) {
        ApiResponce apiResponce = productServise.delete(id);
        return ResponseEntity.status(apiResponce.isSuccess() ? 203 : 409).body(apiResponce);
    }
}
