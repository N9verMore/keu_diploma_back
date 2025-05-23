package org.mitit.keu.core.service;

import org.mitit.keu.core.repositories.MilitaryManRepository;
import org.mitit.keu.core.entities.FamilyMember;
import org.mitit.keu.core.entities.MilitaryMan;
import org.mitit.keu.core.entities.Quota;
import org.mitit.keu.core.entities.enums.QuotaType;
import org.mitit.keu.core.entities.enums.SexEnum;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MilitaryManService {
    private EntityManager entityManager;
    private final MilitaryManRepository militaryManRepository;

    private FamilyMemberService familyMemberService;

    private RankService rankService;

    public List<MilitaryMan> findAll() {
        return militaryManRepository.findAll();
    }

    public Page<MilitaryMan> findAll(Pageable pageable) {
        return militaryManRepository.findAll(pageable);
    }

    public Optional<MilitaryMan> findById(Long id) {
        return militaryManRepository.findById(id);
    }

//    public List<MilitaryMan> findByIdSet(List<Long> customerID) {
//        return customerID.stream().map(this::findById).collect(Collectors.toList());
//    }

    public Page<MilitaryMan> freeFind(String query, Pageable pageable) {
        return militaryManRepository.freeSearch(query, pageable);
    }

    public List<MilitaryMan> findByGarrison(String query) {
        return militaryManRepository.findAllByGarrison(query);
    }

    public List<MilitaryMan> findQueueTypeByGarrison(String garrison, String type) {
        return militaryManRepository.findQueueTypeByGarrison(garrison, type);
    }

    public List<MilitaryMan> getTop() {
        return militaryManRepository.findFirst20ByOrderByAccountingDate();
    }

    public List<MilitaryMan> findAllByQuotaType(String quotaType) {
        return militaryManRepository.findAllByQuotaType(quotaType);
    }

    public MilitaryMan save(MilitaryMan militaryMan) {
        addPreviewId(militaryMan);
        if (militaryMan.getAccountingDate() == null)
            militaryMan.setAccountingDate(LocalDateTime.now());

//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        militaryMan.setCreatedByIpn("1234567021");
        militaryMan.setCreateDate(LocalDateTime.now());
        return militaryManRepository.save(militaryMan);
    }

    public MilitaryMan saveAndFlush(MilitaryMan militaryMan) {
        addPreviewId(militaryMan);
        return militaryManRepository.saveAndFlush(militaryMan);
    }

    public void saveAll(Iterable<MilitaryMan> militaryMen) {
        for (MilitaryMan militaryMan : militaryMen) {
            entityManager.detach(militaryMan);
            addPreviewId(militaryMan);
            militaryMan.setCreateDate(LocalDateTime.now());
            militaryMan.setId(null);

        }
        militaryManRepository.saveAll(militaryMen);
    }

    public void flush() {
        militaryManRepository.flush();
    }

    public void delete(MilitaryMan militaryMan) {
        militaryManRepository.delete(militaryMan);
    }

    public void deleteById(Long id) {
        militaryManRepository.deleteById(id);
    }

    public void deleteAll() {
        militaryManRepository.deleteAll();
    }

    public long count() {
        return militaryManRepository.count();
    }

    public long countAllAfterDate(LocalDateTime afterDate) {
        return militaryManRepository.countByCreateDateAfter(afterDate);
    }

    public Page<MilitaryMan> getAllToday(Pageable pageable) {
        return militaryManRepository.findAllByCreateDateAfter(LocalDate.now().atStartOfDay(), pageable);
    }


    public boolean existsById(Long id) {
        return militaryManRepository.existsById(id);
    }

    public void addPreviewId(MilitaryMan militaryMan) {
        Optional<MilitaryMan> militaryManInDB = findByIpn(militaryMan.getIpn());
        if (militaryManInDB.get().getId() != -1) {
            if (militaryManInDB.get().equals(militaryMan)) {
                militaryMan.setPreview_id(-1);
            }
            militaryMan.setPreview_id(militaryManInDB.get().getId());
        } else {
            militaryMan.setPreview_id(-1);
        }

    }


    public Optional<MilitaryMan> findByIpn(String ipn) {
        List<MilitaryMan> mm = militaryManRepository.findAllByIpn(ipn).stream()
                .sorted(Comparator.comparing(MilitaryMan::getCreateDate).reversed())
                .collect(Collectors.toList());
        if (mm.size()>0)
            return Optional.of(mm.get(0));
        MilitaryMan m = new MilitaryMan();
        m.setId((long) -1);
        return Optional.of(m);
    }

    public int calculateRoomCount(MilitaryMan militaryMan) {
        int militaryManRoomCount = militaryMan.getRoomCount();
        List<FamilyMember> familyMembers = militaryMan.getFamilyMembers();
        boolean isHaveYoungBoy = familyMembers.stream().anyMatch(x -> x.getSex().equals(SexEnum.MALE) &&
                x.getBirthDate().isBefore(x.getBirthDate().withYear(LocalDate.now().getYear())));
        boolean isHaveYoungGirl = familyMembers.stream().anyMatch(x -> x.getSex().equals(SexEnum.FEMALE) &&
                x.getBirthDate().isBefore(x.getBirthDate().withYear(LocalDate.now().getYear())));
        if (isHaveYoungBoy && isHaveYoungGirl) {
            militaryManRoomCount += 2;
        } else if (isHaveYoungBoy || isHaveYoungGirl){
            militaryManRoomCount++;
        }

        boolean isColonel = rankService.findAll().stream().anyMatch(x -> x.getShortName().startsWith("п-к"));
        boolean isGeneral = rankService.findAll().stream().anyMatch(x -> x.getShortName().startsWith("г-л"));
        boolean isGeneralOfArmy = rankService.findAll().stream().anyMatch(x -> x.getShortName().startsWith("ГЕНЕРАЛ"));
        if (isColonel || isGeneral || isGeneralOfArmy) {
            militaryManRoomCount++;
        }
        return militaryManRoomCount;
    }

    public double calculateCompensation(MilitaryMan militaryMan) {
        double pricePerMeter = militaryMan.getWork().getGarrison().getPricePerMeter();
        if (militaryMan.getRoomCount() == 1) {
            return 40 * pricePerMeter;
        }
        Quota militaryManQuota = militaryMan.getQuota();
        int dPl = 0;
        if (militaryManQuota.getType().equals(QuotaType.OUTOFQUEUE) && !militaryManQuota.getName().equals("суддя")) {
            dPl = 10;
        }
        double KyivCityCoefficient = 1.75;
        double b0 = pricePerMeter * KyivCityCoefficient;// static cost of 1 square of flat in hryvna * koef of city
        return (13.65 * familyCount(militaryMan) + 17 + dPl) * b0;
    }

    public long familyCount(MilitaryMan militaryMan){
        return  militaryMan.getFamilyMembers().size() + 1;
    }

    public double calculateHousingRentCompensation(MilitaryMan militaryMan) {
        double rentCompensation = militaryMan.getWork().getGarrison().getHousingRentCompensation();
        if (militaryMan.getFamilyMembers().size() > 2) {
            return rentCompensation * 1.5;
        }
        return rentCompensation;
    }
    public String[] getExhaustInfo(MilitaryMan militaryMan) {
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return new String[]{LocalDate.now().format(pattern), militaryMan.getRank().getName(), militaryMan.getSurname(),
                militaryMan.getName(), militaryMan.getThirdName(), militaryMan.getWork().getGarrison().getName(),
                militaryMan.getApartmentFileDate().format(pattern), String.valueOf(familyCount(militaryMan)),
                militaryMan.getWork().getWorkPlace(),
                militaryMan.getFamilyMembers().stream().map(f -> f.getSurname() + " " + f.getName() + " " + f.getThirdName() + "\n").collect(Collectors.joining()),
                militaryMan.getWork().getAccountingPlace(), militaryMan.getQuota().getName(),
                militaryMan.getQuotaDate().format(pattern), militaryMan.getQuota().getType().getName(),
                String.valueOf(militaryMan.getGeneralQueue()), String.valueOf(militaryMan.getQuotaQueue()),
                String.valueOf(militaryMan.getSurname().hashCode())};
    }
}