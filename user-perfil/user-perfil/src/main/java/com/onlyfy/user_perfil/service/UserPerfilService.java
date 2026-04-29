package com.onlyfy.user_perfil.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.List;
import com.onlyfy.user_perfil.model.UserPerfilModel;
import com.onlyfy.user_perfil.repository.UserPerfilRepository;
@SpringBootApplication
public class UserPerfilService {
    @Autowired
    private UserPerfilRepository userRepository;
    public List<UserPerfilModel> listarTodos() {
        return userRepository.findAll();
    }
    public UserPerfilModel crear(UserPerfilModel perfil) {
        return userRepository.save(perfil);
    }

}