package uz.pdp.finaltask.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.finaltask.entity.Attachment;

public interface AttachmentRepo extends JpaRepository<Attachment, Long> {

}
