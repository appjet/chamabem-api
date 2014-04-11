package com.chamabem.service;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.chamabem.core.persistence.CBPersistenceService;
import com.chamabem.model.User;

@Stateless
public class UserService extends CBPersistenceService {
	
	public void register(User user){
		super.persist(user);
	}
	
	public void update(User user){
		super.update(user);
	}
    
    public User findUserByCredentials(String username, String password){
    	Query query = (Query) em.createQuery("SELECT u FROM User u WHERE u.username = ?1 AND u.password = ?2");
    	query.setParameter(1, username);
    	query.setParameter(2, password);

    	try{
    		User user = (User) query.getResultList().get(0);
    		return user;
    	} catch(IndexOutOfBoundsException e){
    		return null;
    	}
    }
    
    public User findUserByUsername(String username){
    	Query query = (Query) em.createQuery("SELECT u FROM User u WHERE u.username = ?1");
    	query.setParameter(1, username);

    	try{
    		User user = (User) query.getResultList().get(0);
    		return user;
    	} catch(IndexOutOfBoundsException e){
    		return null;
    	}
    }
    
    public void updateUser(User user){
    	super.update(user);
    }
}