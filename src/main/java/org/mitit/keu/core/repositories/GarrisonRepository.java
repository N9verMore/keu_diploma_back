package org.mitit.keu.core.repositories;

import org.mitit.keu.core.entities.Garrison;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GarrisonRepository extends JpaRepository<Garrison, Long> {
    Garrison findByRegion(String region);
}
