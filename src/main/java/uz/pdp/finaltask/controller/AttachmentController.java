package uz.pdp.finaltask.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.pdp.finaltask.payload.ApiResponce;
import uz.pdp.finaltask.servise.AttachmentServise;

@RestController
@RequestMapping("/photo")
public class AttachmentController {

    @Autowired
    AttachmentServise attachmentServise;

    @PostMapping("/save")
    public HttpEntity<?>savePhoto(MultipartHttpServletRequest request){
        ApiResponce apiResponce = attachmentServise.savePhoto(request);
        return ResponseEntity.ok().body(apiResponce);
    }
}
