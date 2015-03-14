/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package integration;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import model.CompetenceProfile;
import model.Person;

/**
 *
 * @author syst3m
 */
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
@Stateless
public class UserHandleDAO {
    @PersistenceContext(unitName = "mavenprojectiv1201")
    private EntityManager em;
    
    @Inject
    RegisterDAO registerDAO;
    
    @Inject
    CompetenceProfileDAO competenceProfileDAO;
    
    public void findAllUsers(){
       List<Person> personList =  registerDAO.getAllUser();
       List<CompetenceProfile> competenceProfile = competenceProfileDAO.getAllCompetenceProfile();
       
       for(int  i = 0 ; i < competenceProfile.size(); ++i){
           for(int j = 0 ; j < personList.size(); ++j){
               if(competenceProfile.get(i).getUserName().equals(personList.get(j).getUsername())){
                   System.out.println("testtt");
               }
           }
           
       }
           
       }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public RegisterDAO getRegisterDAO() {
        return registerDAO;
    }

    public void setRegisterDAO(RegisterDAO registerDAO) {
        this.registerDAO = registerDAO;
    }

    public CompetenceProfileDAO getCompetenceProfileDAO() {
        return competenceProfileDAO;
    }

    public void setCompetenceProfileDAO(CompetenceProfileDAO competenceProfileDAO) {
        this.competenceProfileDAO = competenceProfileDAO;
    }
    }
    

