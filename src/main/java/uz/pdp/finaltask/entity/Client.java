package uz.pdp.finaltask.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import uz.pdp.finaltask.entity.template.AbsNameEntity;

import javax.persistence.Column;
import javax.persistence.Entity;

@RequiredArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Client extends AbsNameEntity {
    @Column(nullable = false)
    private String phoneNumber;

}
