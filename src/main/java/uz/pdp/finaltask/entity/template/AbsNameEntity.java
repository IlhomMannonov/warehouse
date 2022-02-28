package uz.pdp.finaltask.entity.template;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;


@AllArgsConstructor
@MappedSuperclass
@Getter
@Setter
@ToString
@NoArgsConstructor
public class AbsNameEntity extends AbsLong {

    @Column(nullable = false)
    private String name;

    public AbsNameEntity(Long id, String name) {
        super(id);
        this.name = name;
    }

}
