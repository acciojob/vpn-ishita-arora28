package com.driver.services.impl;

import com.driver.model.Admin;
import com.driver.model.Country;
import com.driver.model.CountryName;
import com.driver.model.ServiceProvider;
import com.driver.repository.AdminRepository;
import com.driver.repository.CountryRepository;
import com.driver.repository.ServiceProviderRepository;
import com.driver.services.AdminService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    AdminRepository adminRepository1;

    @Autowired
    ServiceProviderRepository serviceProviderRepository1;

    @Autowired
    CountryRepository countryRepository1;

    @Override
    public Admin register(String username, String password) {
      Admin admin=new Admin(username,password);
      adminRepository1.save(admin);
      return admin;
      
    }

    @Override
    public Admin addServiceProvider(int adminId, String providerName) {
        Optional<Admin> optinalAdmin=adminRepository1.findById(adminId);
         Admin admin=optinalAdmin.get();
         ServiceProvider newSP=new ServiceProvider(providerName, admin);
        List<ServiceProvider> lisOfProviders=admin.getServiceProviders();  
        lisOfProviders.add(newSP);
        adminRepository1.save(admin);
        return admin;

    }

    @Override
    public ServiceProvider addCountry(int serviceProviderId, String countryName) throws Exception{
        //add a country under the serviceProvider and return respective service provider
        //country name would be a 3-character string out of ind, aus, usa, chi, jpn. Each character can be in uppercase or lowercase.
        // You should create a new Country object based on the given country name and add it to the country list of the service provider.
        // Note that the user attribute of the country in this case would be null.
        //In case country name is not amongst the above mentioned strings, throw "Country not found" exception
       
        ServiceProvider serviceProvider=serviceProviderRepository1.findById(serviceProviderId).get();
       Country country=new Country();
        countryName=countryName.toUpperCase();
        if(countryName.equals("IND") || countryName.equals("AUS")|| countryName.equals("USA") || countryName.equals("CHI") || countryName.equals("JPN"))
        {
            country.setCountryName(CountryName.valueOf(countryName));
            country.setCode(CountryName.valueOf(countryName).toCode());
        }
        else{
            throw new Exception("Country not found");

        }
        country.setServiceProvider(serviceProvider);
       List<Country> listOfCountries=serviceProvider.getCountryList();
       listOfCountries.add(country);
       serviceProviderRepository1.save(serviceProvider);
       return serviceProvider;
     
    }

    
}
