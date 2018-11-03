package com.dreambooks.service;

import com.dreambooks.model.Bookmark;
import com.dreambooks.model.Role;
import com.dreambooks.model.User;
import com.dreambooks.repository.RoleRepository;
import com.dreambooks.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService{

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private BookmarkService bookmarkService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, BookmarkService bookmarkService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bookmarkService = bookmarkService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public void saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(1);
        Role userRole = roleRepository.findByRole("USER");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        Bookmark bookmark = new Bookmark();
        user.setBookmark(bookmark);

        userRepository.save(user);
        bookmarkService.connectBookmarkToUser(user, bookmark);
    }

    public Set<User> getAllUsers() {
        Set<User> users = new HashSet<>();
        userRepository.findAll().iterator().forEachRemaining(users::add);
        return users;
    }

    public User getUserById(Long id) {
        Optional<User> optionalBook = userRepository.findById(id);

        return optionalBook.get();
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public void updateUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }
    
    public Set<User> getMaxFiveNewUsers() {
        return userRepository.getMaxFiveNewUsers();
    }

    public Set<User> getUsersByFirstAndLastName(String name) {
        return userRepository.findUsersWithPartOfNames(name);
    }

}
