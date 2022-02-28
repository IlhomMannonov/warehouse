package uz.pdp.finaltask.servise;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.pdp.finaltask.entity.Attachment;
import uz.pdp.finaltask.entity.AttachmentContent;
import uz.pdp.finaltask.payload.ApiResponce;
import uz.pdp.finaltask.repo.AttachmentContentRepo;
import uz.pdp.finaltask.repo.AttachmentRepo;

import java.util.Iterator;

@Service
public class AttachmentServise {

    @Autowired
    AttachmentRepo attachmentRepo;
    @Autowired
    private AttachmentContentRepo attachmentContentRepo;


    @SneakyThrows
    @Transactional
    public ApiResponce savePhoto(MultipartHttpServletRequest multipart) {
        Iterator<String> fileNames = multipart.getFileNames();
        MultipartFile file = multipart.getFile(fileNames.next());
        Attachment attachment = new Attachment();
        if (file != null) {

            attachment.setName(file.getName());
            attachment.setSize(file.getSize());
            attachment.setContentType(file.getContentType());
            Attachment savedAttachment = attachmentRepo.save(attachment);
            attachmentContentRepo.save(new AttachmentContent(file.getBytes(), savedAttachment));
        } else {
            return new ApiResponce(false, "Error");

        }
        return new ApiResponce(true, "ok", attachment.getId());

    }
}
