package com.tharun.application;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

@Service
public class AddressBookService{
	
	@Autowired
	private UserRepository userRepo;
	
	//<------------- POST FUNCTIONALITY ------------------------->
	
    @Caching(evict = {
            @CacheEvict(value = "getEmail", allEntries = true),
            @CacheEvict(value = "getContact", allEntries = true),
            @CacheEvict(value = "getAll", allEntries = true)
    })
	
	public Object AddDetails(User user) {
		if(userRepo.findByEmail(user.getEmail()).size()!=0 && userRepo.findByContact(user.getContact()).size()!=0)
			new ErrorHandling(2, List.of("EmailError : Duplicate Email Address found!","ContactError : Duplicate Contact Number found!"));
		
		if(userRepo.findByEmail(user.getEmail()).size()!=0)
			return new ErrorHandling(1, List.of("EmailError : Duplicate Email Address Found"));
		
		if(userRepo.findByContact(user.getContact()).size()!=0)
			return new ErrorHandling(1, List.of("ContactError : Duplicate Contact Number found"));
		
		return userRepo.save(user);
	}
    
	//<------------- GET FUNCTIONALITY ------------------------->
	
    @Cacheable("getName")
    public Object getByName(String param) {
    	return userRepo.findByName(param, param);
    }
    
    @Cacheable("getAll")
    public Object getAll() {
    	return userRepo.findAll();
    }
    
    @Cacheable("getEmail")
    public Object getEmail(String param) {
    	return userRepo.findByEmail(param);
    }
    
    @Cacheable("getContact")
    public Object getContact(String param) {
    	return userRepo.findByContact(param);
    }
    
  //<------------- DELETE FUNCTIONALITY ------------------------->
    
    @Caching(evict = {
            @CacheEvict(value = "getEmail", allEntries = true),
            @CacheEvict(value = "getContact", allEntries = true),
            @CacheEvict(value = "getAll", allEntries = true)
    })
    
	public Object deleteDetails(String param) {
		User s=userRepo.findByEmailOrPhone(param,param);
		if(s!=null) {
			userRepo.deleteUserByEmail(s.getEmail());
			return "Deletion Success";
		}
		return new ErrorHandling(2, List.of("EmailNotFoundError : Record with specified email not found",
				"ContactNotFoundError : Record with specified contact not found"));
	}
	
  //<------------- PUT FUNCTIONALITY ------------------------->	
	
    @Caching(evict = {
            @CacheEvict(value = "getEmail", allEntries = true),
            @CacheEvict(value = "getContact", allEntries = true),
            @CacheEvict(value = "getAll", allEntries = true)
    })
	public Object updateDetails(User user) {
		
		if(userRepo.findByContact(user.getContact()).size()>=2 && userRepo.findByEmail(user.getEmail()).size()>=2)
			return new ErrorHandling(2, List.of("EmailError : Duplicate Email Address found!","ContactError : Duplicate Contact Number found!"));

		if(userRepo.findByEmail(user.getEmail()).size()>=2)
			return new ErrorHandling(1, List.of("EmailError : Duplicate Email Address found!"));
		
		if(userRepo.findByContact(user.getContact()).size()>=2)
			return new ErrorHandling(1, List.of("ContactError : Duplicate Contact Number found!"));
		
		if(userRepo.findByEmail(user.getEmail()).size()==0 && userRepo.findByContact(user.getContact()).size()==0) {
			//userRepo.save(user);
			return new ErrorHandling(2,List.of("EmailNotFoundError : Record with specified email not found",
					"ContactNotFoundError : Record with specified contact not found"));
		}
		
		if(userRepo.findByEmail(user.getEmail()).size()==1 || userRepo.findByContact(user.getContact()).size()==1) {
			userRepo.save(user);
			try {
				Long l=Long.parseLong(user.getContact());
				return "Update successful with Contact Number: "+l;
			}
			catch(Exception e) {
				return "Update successful with Email Address: "+user.getEmail();
			}
		}
		
		return null;
	}
}