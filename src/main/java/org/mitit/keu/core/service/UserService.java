package org.mitit.keu.core.service;

import org.mitit.keu.core.repositories.UserRepository;
import org.mitit.keu.core.entities.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Service

@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final HttpServletRequest request;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public void delete(User user) {
        userRepository.delete(user);
    }

    public boolean existsById(Long id) {
        return userRepository.existsById(id);
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    public User saveAndFlush(User user) {
        return userRepository.saveAndFlush(user);
    }

    public void flush() {
        userRepository.flush();
    }

    public void saveAll(Iterable<User> users) {
        userRepository.saveAll(users);
    }

    public long count() {
        return userRepository.count();
    }

    public void deleteAll() {
        userRepository.deleteAll();
    }

    public Optional<User> findByUsername(String ipn) {
        return  userRepository.findByUsername(ipn);
    }

//
//    @Transactional
//    public User getByEmail(String email) {
//        return userRepository.getByLogin(email);
//    }
//
//    @Transactional
//    public User registerNewUserAccount(User user) throws Exception {
//        if (emailExist(user.getLogin())) {
//            throw new Exception();
//        }
//        User newUser = new User();
//
//        System.out.println(user);
//        System.out.println(user.getPassword());
//        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
//
//        newUser.setLogin(user.getLogin());
//        newUser.setFirstName(user.getFirstName());
//        newUser.setLastName(user.getLastName());
//        newUser.setThirdName(user.getThirdName());
//        return save(newUser);
//    }
//
//    public User createUserAccount(User accountDto, BindingResult result) {
//        User registered = null;
//        try {
//            registered = registerNewUserAccount(accountDto);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return registered;
//    }
//
//
//    public List<GrantedAuthority> getAuthorities(Roles role) {
//        List<GrantedAuthority> authorities = new ArrayList<>();
//        authorities.add(new SimpleGrantedAuthority(role.toString()));
//        System.out.println(role.getName());
//        return authorities;
//    }
//
//    private String getClientIP() {
//        String xfHeader = request.getHeader("X-Forwarded-For");
//        if (xfHeader == null) {
//            return request.getRemoteAddr();
//        }
//        return xfHeader.split(",")[0];
//    }
//
//    private boolean emailExist(String email) {
//        User user = userRepository.getByLogin(email);
//        if (user != null) {
//            return true;
//        }
//        return false;
//    }

}