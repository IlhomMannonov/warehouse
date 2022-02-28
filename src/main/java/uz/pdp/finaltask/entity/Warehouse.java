package uz.pdp.finaltask.entity;

import lombok.*;
import uz.pdp.finaltask.entity.template.AbsNameEntity;

import javax.persistence.Entity;

@RequiredArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Warehouse extends AbsNameEntity {

    private boolean active;
}
