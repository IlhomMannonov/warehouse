package uz.pdp.finaltask.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.finaltask.entity.Client;

public interface ClientRepo extends JpaRepository<Client, Long> {
}
