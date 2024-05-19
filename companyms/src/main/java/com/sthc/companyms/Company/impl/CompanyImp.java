package com.sthc.companyms.Company.impl;


import com.sthc.companyms.Company.Company;
import com.sthc.companyms.Company.CompanyRepository;
import com.sthc.companyms.Company.CompanyService;
import com.sthc.companyms.Company.clients.ReviewClients;
import com.sthc.companyms.Company.dto.ReviewMessage;
import jakarta.ws.rs.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyImp implements CompanyService {

    private CompanyRepository companyRepository;
    private ReviewClients reviewClients;

    public CompanyImp(CompanyRepository companyRepository,ReviewClients reviewClients) {
        this.companyRepository = companyRepository;
        this.reviewClients=reviewClients;
    }

    @Override
    public List<Company> findAll() {
        return companyRepository.findAll();
    }

    @Override
    public void createCompany(Company company) {
        companyRepository.save(company);
    }

    @Override
    public Company getCompanyById(Long id) {
        return companyRepository.findById(id).orElse(null);
    }

    @Override
    public boolean deleteCompanyById(Long id) {
        try{
            companyRepository.deleteById(id);
            return true;
        }catch(Exception e){
            return false;
        }
    }

    @Override
    public boolean updateCompany(Long id, Company updateCompany) {
        Optional<Company> companyOptional = companyRepository.findById(id);
        if (companyOptional.isPresent()){
            Company company = companyOptional.get();
            company.setName(updateCompany.getName());
            company.setDescription(updateCompany.getDescription());
            companyRepository.save(company);
            return true;
        }
        return false;
    }

    @Override
    public void updateCompanyRating(ReviewMessage reviewMessage) {
        System.out.println(reviewMessage.getDescription());
        Company company = companyRepository.findById(reviewMessage.getCompanyId()).
                orElseThrow(()->
                        new NotFoundException("Company not found" + reviewMessage.getCompanyId()));
        Double averageRating = reviewClients.getAverageRatingForCompany(reviewMessage.getCompanyId());
        company.setRating(averageRating);
        companyRepository.save(company);
    }
}
