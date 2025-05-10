package org.mitit.keu.core.repositories;

import org.mitit.keu.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

//    User getByLogin(String email);
//
//    List<User> getAllByLastName(String surname);
    Optional<User> findByUsername(String ipn);

}
