package org.mitit.keu.core.service;

import org.mitit.keu.core.entities.MilitaryMan;
import org.mitit.keu.core.entities.Registry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QueueService {
    private static MilitaryManService militaryManService;
    private static QueueXlsCreateService queueXlsCreateService;
    private static RegistryService registryService;

    @Autowired
    public void setRatingXlsCreateService(QueueXlsCreateService queueXlsCreateService) {
        this.queueXlsCreateService = queueXlsCreateService;
    }

    @Autowired
    public void setMilitaryManService(MilitaryManService militaryManService) {
        this.militaryManService = militaryManService;
    }
    @Autowired
    public void setRegistryService(RegistryService registryService) {
        this.registryService = registryService;
    }

    public static List<MilitaryMan> getQueue(String garrison, String queueType) {
        List<MilitaryMan> queueInGarrison = new ArrayList<>();
        switch (queueType) {
            case "general":
                queueInGarrison = militaryManService.findByGarrison(garrison)
                        .stream().filter(m -> m.getPreview_id()==-1).sorted(
                                Comparator.comparing(m -> m.getAccountingDate()))
                        .collect(Collectors.toList());
                Collections.reverse(queueInGarrison);
                break;
            case "firstinpriority":
            case "outofqueue":
                queueInGarrison = militaryManService.findQueueTypeByGarrison(garrison, queueType.toUpperCase())
                        .stream().sorted(
                                Comparator.comparing(m -> m.getQuotaQueue()))
                        .collect(Collectors.toList());
                break;
            case "compensation":
                queueInGarrison = militaryManService.findByGarrison(garrison)
                        .stream().filter(MilitaryMan::isWantCompensation).sorted(
                                Comparator.comparing(MilitaryMan::getAccountingDate).reversed()
                                        .thenComparing(MilitaryMan::getServiceFrom).reversed())
                        .collect(Collectors.toList());
                break;
        }
        return queueInGarrison;
    }

    public static List<Registry> getReceivedQueue(String garrison, String queueType) {
        List<Registry> registry = new ArrayList<>();
        switch (queueType) {
            case "all":
                registry = registryService.findAllByGarrison(garrison).stream().sorted(Comparator.comparing(Registry::getReceiveDate))
                        .collect(Collectors.toList());
                break;
            case "flat":
                registry = registryService.findByReceivedFlat(garrison).stream().sorted(Comparator.comparing(Registry::getReceiveDate))
                    .collect(Collectors.toList());
            break;
            case "money":
                registry = registryService.findByReceivedMoney(garrison).stream().sorted(Comparator.comparing(Registry::getReceiveDate))
                        .collect(Collectors.toList());
                break;
        }
        return registry;
    }

}
