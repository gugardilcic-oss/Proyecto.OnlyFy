
package com.onlyfy.jam_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.onlyfy.jam_service.model.JamModel;

public interface JamRepository extends JpaRepository<JamModel, Long> {

}