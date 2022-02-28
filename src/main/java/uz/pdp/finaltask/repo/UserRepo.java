package uz.pdp.finaltask.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.finaltask.entity.User;

public interface UserRepo extends JpaRepository<User, Long> {
}
