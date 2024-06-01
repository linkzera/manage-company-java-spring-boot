package com.company.company.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.company.company.entity.Company;
import com.company.company.repository.CompanyRepository;
import org.springframework.data.domain.Sort;

@Service
public class CompanyService {
    private CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public Company saveCompany(Company company) {
        return companyRepository.save(company);
    }

    public Company getCompany(Long id) {
        return companyRepository.findById(id).orElse(null);
    }

    public List<Company> deleteCompany(Long id) {
        companyRepository.deleteById(id);
        return getCompanies();
    }

    public Company updateCompany(Long id, Company company) {
        var existingCompany = companyRepository.findById(id).orElse(null);
        if (existingCompany == null) {
            return null;
        }
        existingCompany.setName(company.getName());
        existingCompany.setAddress(company.getAddress());
        existingCompany.setCity(company.getCity());
        existingCompany.setCountry(company.getCountry());
        existingCompany.setEmail(company.getEmail());
        existingCompany.setPhoneNumber(company.getPhoneNumber());
        return companyRepository.save(existingCompany);
    }

    public List<Company> getCompanies() {
        return companyRepository.findAll(Sort.by("name"));
    }

}
