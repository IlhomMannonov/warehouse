package uz.pdp.finaltask.entity;

import lombok.*;
import uz.pdp.finaltask.entity.template.AbsNameEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Category extends AbsNameEntity {

    @ManyToOne
    private Category category;

    private boolean active;

    public Category(Long id, String name, Category category, boolean active) {
        super(id, name);
        this.category = category;
        this.active = active;
    }

    public Category(String name, Category category, boolean active) {
        super(name);
        this.category = category;
        this.active = active;
    }
}
