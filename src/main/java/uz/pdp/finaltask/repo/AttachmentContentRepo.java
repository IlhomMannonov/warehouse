package uz.pdp.finaltask.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.finaltask.entity.AttachmentContent;

public interface AttachmentContentRepo extends JpaRepository<AttachmentContent, Long> {
}
