package uz.pdp.finaltask.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.finaltask.payload.ApiResponce;
import uz.pdp.finaltask.payload.CategoryDto;
import uz.pdp.finaltask.servise.CategoryServise;


@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    CategoryServise categoryServise;

    @PostMapping("/addOrEdit")
    public HttpEntity<?> addOrEdit(@RequestBody CategoryDto categoryDto){
        ApiResponce apiResponce = categoryServise.add(categoryDto);
        return ResponseEntity.status(apiResponce.isSuccess()?200:409).body(apiResponce);
    }

    @DeleteMapping("/delete/{id}")
    public HttpEntity<?>delete(@PathVariable Long id){
        ApiResponce apiResponce = categoryServise.delete(id);
        return ResponseEntity.status(apiResponce.isSuccess()?203:409).body(apiResponce);
    }

    @GetMapping("/get")  // parent id orqali bitta otaga tegishli categorylarni korish agar id berilmasa otasi null categorylar keladi
    public HttpEntity<?>get(@RequestParam(value = "parent", required = false) Long parentId){
        ApiResponce apiResponce = categoryServise.get(parentId);
        return ResponseEntity.status(apiResponce.isSuccess()?200:409).body(apiResponce);
    }
}
