package com.driver.services.impl;

import com.driver.model.Country;
import com.driver.model.CountryName;
import com.driver.model.ServiceProvider;
import com.driver.model.User;
import com.driver.repository.CountryRepository;
import com.driver.repository.ServiceProviderRepository;
import com.driver.repository.UserRepository;
import com.driver.services.UserService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository3;
    @Autowired
    ServiceProviderRepository serviceProviderRepository3;
    @Autowired
    CountryRepository countryRepository3;

    @Override
    public User register(String username, String password, String countryName) throws Exception{
 //create a user of given country. 
 //The originalIp of the user should be "countryCode.userId" and return the user.
 // Note that right now user is not connected and thus connected would be false and maskedIp would be null
        //Note that the userId is created automatically by the repository layer
        
        User user =new User();
        Country c=new Country();
        user.setUsername(username);
        user.setPassword(password);

        countryName=countryName.toUpperCase();
        if(countryName.equals("IND") || countryName.equals("AUS") || countryName.equals("USA") || countryName.equals("CHI") || countryName.equals("JPN")){
            c.setCountryName(CountryName.valueOf(countryName));
            c.setCode(CountryName.valueOf(countryName).toCode());

        }
        else{
            throw new Exception("Country not found");
        }
        c.setUser(user);
        user.setCountry(c);
        user.setMaskedIp(null);
        user.setConnected(false);

        user.setOriginalIp(c.getCode()+"."+userRepository3.save(user).getId());
        userRepository3.save(user);
        return user;
        
    }

    @Override
    public User subscribe(Integer userId, Integer serviceProviderId) {
        //subscribe to the serviceProvider by adding it to the list of providers and return updated User
        //ServiceProvider serviceProvider=new ServiceProvider();
        User user=userRepository3.findById(userId).get();
        List<ServiceProvider> listOfProviders=user.getServiceProviderList();
        ServiceProvider currServiceProvider=serviceProviderRepository3.findById(serviceProviderId).get();

        listOfProviders.add(currServiceProvider);
        user.setServiceProviderList(listOfProviders);
        userRepository3.save(user);

        return user;

    }
}
