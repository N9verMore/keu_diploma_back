package org.mitit.keu.core.repositories;

import org.mitit.keu.entities.Rank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RankRepository extends JpaRepository<Rank, Long> {


}
