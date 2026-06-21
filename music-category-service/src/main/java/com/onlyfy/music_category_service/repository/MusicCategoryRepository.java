package com.onlyfy.music_category_service.repository;

import com.onlyfy.music_category_service.model.MusicCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MusicCategoryRepository extends JpaRepository<MusicCategory, Integer> {
}