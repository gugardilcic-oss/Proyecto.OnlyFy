
package com.onlyfy.login_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.onlyfy.login_service.model.InicioSesionModel;

@Repository
public interface InicioSesionRepository extends JpaRepository<InicioSesionModel, Long> {

    // Este método buscará automáticamente en la base de datos por el campo NombreUsuario
    InicioSesionModel findByNombreUsuario(String nombreUsuario);

}