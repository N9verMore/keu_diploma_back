package org.mitit.keu.core.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;
import org.mitit.keu.core.entities.enums.Provided;
import org.mitit.keu.core.entities.enums.QuotaType;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@Table(name = "military_man")
public class MilitaryMan implements Serializable { // TODO: 25.11.2023 Refactor length fields

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "surname", nullable = false, length = 100)
    private String surname;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "thirdName", nullable = false, length = 100)
    private String thirdName;

    @Column(name = "address")
    private String address;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "accounting_date", nullable = false)
    @DateTimeFormat(pattern = "dd-mm-yyyy")
    private LocalDateTime accountingDate;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Quota.class)
    @JoinColumn(name = "quota_id", nullable = false)
    private Quota quota = new Quota("Без пільг", "Без пільг", QuotaType.NONE);

    @Column(name = "quota_date", nullable = true)
    @DateTimeFormat(pattern = "dd-mm-yyyy")
    private LocalDateTime quotaDate;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Rank.class)
    @JoinColumn(name = "rank_id", nullable = false)
    private Rank rank;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Work.class)
    @JoinColumn(name = "work_id", nullable = false)
    private Work work;

    @Lob
    @Column(name = "info", nullable = true)
    @Type(type = "org.hibernate.type.TextType")
    private String info;

    //@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, targetEntity = QueueNumber.class)
    @Column(name = "general_queue")
    private int generalQueue;

    //@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, targetEntity = QueueNumber.class)
    @Column(name = "quota_queue")
    private int quotaQueue;

    @Column(name = "registrated", nullable = false)
    @ColumnDefault("false")
    private boolean registrated;

    @Column(name = "rozshirennya", nullable = false)
    @ColumnDefault("false")
    private boolean rozshirennya;

    @Column(name = "want_compensation", nullable = false)
    @ColumnDefault("false")
    private boolean wantCompensation;

    @Column(name = "death_date")
    @DateTimeFormat(pattern = "dd-mm-yyyy")
    private LocalDateTime deathDate;

    @Column(name = "ipn", nullable = false)
    private String ipn;

    @Enumerated(EnumType.STRING)
    @Column(name = "provided", nullable = false)
    private Provided provided = Provided.NO;

    @Column(name = "create_date", nullable = false)
    @UpdateTimestamp
    private LocalDateTime createDate;

    @Column(name = "created_by_ipn", nullable = false)
    private String createdByIpn;

    @Column(name = "room_count", nullable = false)
    @ColumnDefault("1")
    private int roomCount;

    @Column(name = "family_war_2022", nullable = false)
    @ColumnDefault("false")
    private boolean familyWar2022;

    @Column(name = "service_from", nullable = false)
    @DateTimeFormat(pattern = "dd-mm-yyyy")
    private LocalDateTime serviceFrom;

    @Column(name = "service_until")
    @DateTimeFormat(pattern = "dd-mm-yyyy")
    private LocalDateTime serviceUntil;

    @Column(name = "apartment_file_date")
    @DateTimeFormat(pattern = "dd-mm-yyyy")
    private LocalDateTime apartmentFileDate;

    @Column(name = "apartment_file_number")
    private String apartmentFileNumber;

    @OneToMany
    @JoinColumn(name = "military_man_id")
    private List<FamilyMember> familyMembers = new ArrayList<>();

    @Column(name = "preview_id", nullable = false, length = 20)
    @ColumnDefault("-1")
    private long preview_id;


}
