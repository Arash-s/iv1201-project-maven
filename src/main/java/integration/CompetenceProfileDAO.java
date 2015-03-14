/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package integration;

import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.Competence;
import model.Person;
import java.util.ArrayList;
import javax.persistence.TypedQuery;
import model.Availability;
import model.CompetenceProfile;

/**
 *
 * @author syst3m
 */
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
@Stateless
public class CompetenceProfileDAO{
    @PersistenceContext(unitName = "mavenprojectiv1201")
    private EntityManager em;
    
    private double yearsOfExperience;
    
    @Inject
    RegisterDAO registerDAO;
    
    @Inject
    CompetenceDAO competenceDAO;
    
    public Competence getCompetence(String competenceName){
        Competence competence = em.find(Competence.class, competenceName);
        return competence;
    }
    
    public void createCompetenceProfile(String userName ,List<Competence> selectedcompetence , Date from , Date to,List<String> experienceList){
        
        Person person = registerDAO.findPerson(userName);
        
        List<Competence> listofCompetence = getAllCompetence(selectedcompetence);
        createAvilability(from,to,person);
        
         for(int i = 0 ; i < listofCompetence.size(); ++i){
             CompetenceProfile competenceProfile = new CompetenceProfile();     
             yearsOfExperience = Double.valueOf(experienceList.get(i));           
             competenceProfile.setUserName(person);
             competenceProfile.setCompetenceId(listofCompetence.get(i));
             competenceProfile.setYearsOfExperience(yearsOfExperience);
             em.persist(competenceProfile);
           
        }        
    }
    
    public List<Competence> getAllCompetence(List<Competence> list){
        List<Competence> listofCompetence = new ArrayList<Competence>();
        for(int  i = 0 ; i< list.size(); ++i){
            Competence competence = competenceDAO.findCompetence(list.get(i).getName());
            listofCompetence.add(competence);
        }
        return listofCompetence;
    }
    
    private void createAvilability(Date ftom, Date to, Person person){
        Availability avibilityla = new Availability();
        avibilityla.setFromDate(ftom);
        avibilityla.setToDate(to);
        avibilityla.setUsername(person);
        em.persist(avibilityla);
    }
    
    public List<CompetenceProfile> getAllCompetenceProfile(){
         TypedQuery<CompetenceProfile> competenceProquesrList = em.createNamedQuery("CompetenceProfile.findAll",CompetenceProfile.class);
        List<CompetenceProfile> compProList = competenceProquesrList.getResultList();
        return compProList;
    }
}
