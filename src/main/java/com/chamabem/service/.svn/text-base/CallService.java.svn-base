package com.chamabem.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.hibernate.SQLQuery;
import org.hibernate.Session;

import com.chamabem.core.persistence.CBPersistenceService;
import com.chamabem.model.TaxiCall;
import com.chamabem.model.User;

@Stateless
public class CallService extends CBPersistenceService {
	
	public void includeCall(TaxiCall taxiCall){
		super.persist(taxiCall);	
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
    
    public void updateStatus(TaxiCall taxiCall){
    	super.update(taxiCall);
    }
    
    @SuppressWarnings("unchecked")
	public List<TaxiCall> listTaxiCall(){
    		Session session = (Session) em.getDelegate();
    	    SQLQuery query = session.createSQLQuery("SELECT * FROM TaxiCall");
    	 
    	    List<TaxiCall> listTaxiCall;
    	    try {
    	    	listTaxiCall = query.list();
    	    } catch (NoResultException e) {
    	    	return null;
    	    }
    	 
    	    return listTaxiCall;
    }
}