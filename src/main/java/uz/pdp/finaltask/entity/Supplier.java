package uz.pdp.finaltask.entity;

import lombok.*;
import uz.pdp.finaltask.entity.template.AbsNameEntity;

import javax.persistence.Column;
import javax.persistence.Entity;

@RequiredArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Supplier extends AbsNameEntity {

    private boolean active;

    @Column(nullable = false, unique = true)
    private String phoneNumber;
}
