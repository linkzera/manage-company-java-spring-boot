package com.company.company.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.company.company.entity.Company;

public interface CompanyRepository extends JpaRepository<Company, Long>{}
