package uz.pdp.finaltask.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.finaltask.entity.Product;

import java.util.List;

public interface ProductRepo extends JpaRepository<Product, Long> {
    List<Product>findByCategoryId(Long category_id);


    Page<Product> findByCategoryId(Long category_id,Pageable pageable);
}
