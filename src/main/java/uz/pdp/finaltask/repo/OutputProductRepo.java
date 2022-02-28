package uz.pdp.finaltask.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.finaltask.entity.OutputProduct;

public interface OutputProductRepo extends JpaRepository<OutputProduct, Long> {
    Page<OutputProduct> findAllByOutputId(Long output_id, Pageable pageable);
}
