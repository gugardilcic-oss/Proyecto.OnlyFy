package com.onlyfy.search_service.repository;

import com.onlyfy.search_service.model.SearchRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SearchRepository extends JpaRepository<SearchRecord, Integer> {
}