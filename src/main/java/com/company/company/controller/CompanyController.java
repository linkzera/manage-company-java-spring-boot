package com.company.company.controller;

import org.springframework.web.bind.annotation.RestController;

import com.company.company.entity.Company;
import com.company.company.service.CompanyService;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/company")
public class CompanyController {
  private CompanyService companyService;

  public CompanyController(CompanyService companyService) {
    this.companyService = companyService;
  }

  @PostMapping
  public List<Company> createCompany(@RequestBody @Valid Company entity) {
    companyService.saveCompany(entity);
    return companyService.getCompanies();
  }

  @GetMapping
  public List<Company> findAllCompanies() {
    return companyService.getCompanies();
  }

  @GetMapping("{id}")
  public Company findOneCompany(@PathVariable Long id) {
    Company company = companyService.getCompany(id);

    if (company == null) {
      throw new RuntimeException("Company not found");
    }

    return company;
  }

  @PatchMapping("{id}")
  public Company updateCompany(@PathVariable Long id, @RequestBody Company entity) {
    Company company = companyService.updateCompany(id, entity);

    if (company == null) {
      throw new RuntimeException("Company not found");
    }

    return company;
  }

  @DeleteMapping("{id}")
  public List<Company> deleteCompany(@PathVariable Long id) {
    return companyService.deleteCompany(id);
  }

}
