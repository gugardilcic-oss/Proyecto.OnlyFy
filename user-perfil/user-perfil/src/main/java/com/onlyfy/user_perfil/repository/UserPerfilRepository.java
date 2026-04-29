package com.onlyfy.user_perfil.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.onlyfy.user_perfil.model.UserPerfilModel;

public interface UserPerfilRepository extends JpaRepository<UserPerfilModel, Integer> {
    
}
