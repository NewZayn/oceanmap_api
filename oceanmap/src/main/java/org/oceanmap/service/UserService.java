package org.oceanmap.service;
import org.oceanmap.dto.UserDTO;
import org.oceanmap.dto.UserLoged;
import org.oceanmap.exception.ObjectNotFound;
import org.oceanmap.model.Profile;
import org.oceanmap.model.User;
import org.oceanmap.repository.ProfileRepository;
import org.oceanmap.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Date;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProfileRepository profileRepository;

    public void update(User obj) {
        User user = userRepository.findByID(obj.getId()).orElseThrow(() -> new ObjectNotFound("User not found"));
        updateData(obj, user);
        userRepository.save(user);
    }

    public void save(User user) {
        if (user.getProfile() != null && user.getProfile().getId() == null) {
            profileRepository.save(user.getProfile());
        }
        userRepository.save(user);
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new ObjectNotFound("User Not Found"));
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public UserDTO login(UserLoged userLoged) {
        User client = findUser(userLoged.getEmail(), userLoged.getPassword());
        return createLoggedDTO(client);
    }

    public User findUser(String email, String password) {
        User user = userRepository.findByEmailAndPassword(email, password);
        if (user == null) {
            throw new ObjectNotFound("Usuário não encontrado");
        }
        return user;
    }

    private UserDTO createLoggedDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());
        userDTO.setEnabled(user.getActive());
        return userDTO;
    }

    public User fromDTO(UserDTO obj) {
        Profile profile = new Profile();
        System.out.println(obj.getName());


        return new User(
                obj.getId(),
                obj.getName(),
                obj.getEmail(),
                obj.getPassword(),
                new Date(),
                new Date(),
                Boolean.TRUE,
                profile
        );
    }


    public Boolean findByEmail(String email) {
        User user = userRepository.findByEmail(email);
        return (Boolean) (user != null);
    }



    private void updateData(User newObj, User obj) {
        newObj.setName((obj.getName() == null) ? newObj.getName() : obj.getName());
        newObj.setEmail((obj.getEmail() == null) ? newObj.getEmail() : obj.getEmail());
        newObj.setPassword((obj.getPassword() == null) ? newObj.getPassword() : obj.getPassword());
        newObj.setActive((obj.getActive() == null) ? newObj.getActive() : obj.getActive());
        newObj.setUpdated(new Date());
    }

}
