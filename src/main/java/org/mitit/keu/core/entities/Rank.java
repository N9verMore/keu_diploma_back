package org.mitit.keu.core.entities;

import lombok.*;
import org.mitit.keu.core.entities.enums.RankType;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ranks")
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Rank implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Column(name = "short_name", length = 20, nullable = false)
    private String shortName;

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    private RankType type = RankType.PRESENTMILITARY;


    public Rank(String name, String shortName, RankType type) {
        this.name = name;
        this.shortName = shortName;
        this.type = type;
        //this.privileges = privileges;
    }
}
