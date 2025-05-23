package org.mitit.keu.core.entities;

import org.mitit.keu.core.entities.enums.SexEnum;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "familyMembers")
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class FamilyMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "surname", nullable = false, length = 100)
    private String surname;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "thirdName", nullable = false, length = 100)
    private String thirdName;

    @Column(name = "birth_date", nullable = false)
    @DateTimeFormat(pattern = "dd-mm-yyyy")
    private LocalDateTime birthDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "sex", nullable = false)
    private SexEnum sex;

    public FamilyMember(String surname, String name, String thirdName, LocalDateTime birthDate, SexEnum sex) {
        this.surname = surname;
        this.name = name;
        this.thirdName = thirdName;
        this.birthDate = birthDate;
        this.sex = sex;
    }
}
