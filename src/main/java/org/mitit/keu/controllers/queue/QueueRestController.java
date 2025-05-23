package org.mitit.keu.controllers.queue;

import org.mitit.keu.core.service.MilitaryManService;
import org.mitit.keu.core.entities.MilitaryMan;
import org.mitit.keu.core.service.QueueService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/queue")
@CrossOrigin(origins = "*", maxAge = 3600)
public class QueueRestController {
    public MilitaryManService militaryManService;
    private static List<MilitaryMan> staticMilitaryMEN = new ArrayList<>();
    @GetMapping("/{garrison}/{queueType}")
    public Page<MilitaryMan> getQueue(@PathVariable String garrison,
                                      @PathVariable String queueType,
                                      @RequestParam(required = false, defaultValue = "0") int page,
                                      @RequestParam(required = false, defaultValue = "50") int size) {
        staticMilitaryMEN = QueueService.getQueue(garrison, queueType);
        Pageable pageable;
        if (size == 0) {
            pageable = Pageable.unpaged();
        } else {
            pageable = PageRequest.of(page, size);
        }
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), staticMilitaryMEN.size());

        List<MilitaryMan> pageContent = staticMilitaryMEN.subList(start, end);
        return new PageImpl<>(pageContent, pageable, staticMilitaryMEN.size());
    }

    //admin
//    @PostMapping("/calculate/{garrison}")
//    public void calculateQueue(@PathVariable String garrison) {
//        List<MilitaryMan> militaryManGeneral;
//        List<MilitaryMan> customersPersho;
//        List<MilitaryMan> customersPoza;
//
//        militaryManGeneral = militaryManService.findByGarrison(garrison);
//        militaryManGeneral = militaryManGeneral.stream().sorted(Comparator.comparing(MilitaryMan::getAccountingDate).reversed()
//                        .thenComparing(m -> m.getQuota().getType()).reversed()
//                        .thenComparing(c -> c.getQuotaDate() != null ? c.getQuotaDate() : c.getAccountingDate()))
//                .collect(Collectors.toList());
//        for (int i = 0; i < militaryManGeneral.size(); i++) {
//            militaryManGeneral.get(i).setGeneralQueue((i + 1));
//        }
//        militaryManService.saveAll(militaryManGeneral);
//        customersPersho = new ArrayList<>(militaryManGeneral);
//
//        customersPoza = militaryManGeneral.stream()
//                .filter(c -> c.getQuota().getType().equals(QuotaType.OUTOFQUEUE))
//                .sorted(Comparator.comparing(MilitaryMan::getQuotaDate)
//                        .thenComparing(MilitaryMan::getAccountingDate))
//                .collect(Collectors.toList());
//        for (int i = 0; i < customersPoza.size(); i++) {
//            customersPoza.get(i).setQuotaQueue((i + 1));
//        }
//        militaryManService.saveAll(customersPoza);
//
//        customersPersho = customersPersho.stream()
//                .filter(c -> c.getQuota().getType().equals(QuotaType.FIRSTINPRIORITY))
//                .sorted(Comparator.comparing(MilitaryMan::getQuotaDate)
//                        .thenComparing(MilitaryMan::getAccountingDate))
//                .collect(Collectors.toList());
//        for (int i = 0; i < customersPersho.size(); i++) {
//            customersPersho.get(i).setQuotaQueue((i + 1));
//        }
//        militaryManService.saveAll(customersPersho);
//    }

}
