package com.oracle.determinations.interview.engine.userplugins;

import java.util.List;
import com.oracle.determinations.engine.Entity;
import com.oracle.determinations.engine.Rulebase;
import com.oracle.determinations.interview.engine.InterviewRulebase;
import com.oracle.determinations.interview.engine.InterviewSession;
import com.oracle.determinations.interview.engine.SecurityToken;
import com.oracle.determinations.interview.engine.data.model.InstanceStatus;
import com.oracle.determinations.interview.engine.data.model.InterviewEntityInstance;
import com.oracle.determinations.interview.engine.data.model.InterviewUserData;
import com.oracle.determinations.interview.engine.plugins.InterviewSessionPlugin;
import com.oracle.determinations.interview.engine.plugins.InterviewSessionRegisterArgs;
import com.oracle.determinations.interview.engine.plugins.data.DataAdaptorPlugin;

import java.io.File;

import java.util.StringTokenizer;

public class HMPDataAdaptor implements DataAdaptorPlugin {

    /**
      * Constructor
      */
    public HMPDataAdaptor() {
            System.out.println("HMPDataAdaptor Constructor.");
    }
    
    /** ******************************** */
    /* Web-determinations method calls */
    /** ******************************** */

    /**
      * Returns true, for the data adaptor generates case IDs coming from the
      * 'WD_Case' table
      */
    public boolean dataAdaptorProvidesCaseID() {
        return true;
    }

    /**
      * Returns the saved cases
      */
    public String[] listCases(SecurityToken token, InterviewRulebase rulebase) {
        String[] cases = new String[] {};
        return cases;
    }
    

    
    /**
      * Loads a case by returning the interview user data
      */
    public InterviewUserData load(SecurityToken token, String caseID,
            InterviewRulebase rulebase) {
      
      
      InterviewUserData userData = new InterviewUserData();
      InterviewEntityInstance gentityInstance = userData.getGlobalInstance();
      
      System.out.println( "Global Instance Entity ID : " + gentityInstance.getEntityId() );
      
      try{ 
            Rulebase rb = rulebase.getRulebase();          
            Entity global_entity = rulebase.getRulebase().getGlobalEntity();    
            System.out.println("Start " + global_entity.getName());
           
            Entity Eeap = null;
            Entity Eclinical_data = null;
            Entity EAP_Alerts = null;
            Entity EAP_Packages = null;
            Entity Emedical_test = null;
                
            String contactInfo = caseID;           
            String last_name,first_name, sex = null;
            String age = null;
            String contact_id = null;
          
            InterviewEntityInstance EAP_EntityInstance = null;
            InterviewEntityInstance CLINICAL_DATAInstance = null;
            InterviewEntityInstance EAP_PackageInstance = null;
            InterviewEntityInstance EAP_AlertsInstance = null;
           try{
                  Eeap = rb.getEntity("EAP_Entity");                
                  EAP_Packages = rb.getEntity("EAP_Packages");
                  EAP_Alerts = rb.getEntity("EAP_Alerts");  
                  Eclinical_data = rb.getEntity("CLINICAL_DATA");
                  Emedical_test =  rb.getEntity("medical_test");
               
           }
           catch(Exception e){
             System.out.println("error " + e.getMessage());  
             e.printStackTrace();
           }
           try{ 
           if(Eeap != null)
           {
             System.out.println("checkEntity1 Start" + Eeap.getName());
               //continue ceating InterviewEntitInstance
               
               EAP_EntityInstance = new InterviewEntityInstance("EAP_Entity", "1");
               EAP_EntityInstance.setStatus(InstanceStatus.ADD); 
               EAP_EntityInstance.setContainmentParent(gentityInstance);
               userData.addInstance(EAP_EntityInstance);
               gentityInstance.setContainmentComplete("EAP_Entity",true);
             System.out.println("checkEntity1 End" + Eeap.getName());
           } 
             if(Eclinical_data != null)
             {
               System.out.println("checkEntity2 Start"+ Eclinical_data.getName());
               CLINICAL_DATAInstance = new InterviewEntityInstance("CLINICAL_DATA", "2");
               CLINICAL_DATAInstance.setStatus(InstanceStatus.ADD);
               CLINICAL_DATAInstance.setContainmentParent(gentityInstance);
               userData.addInstance(CLINICAL_DATAInstance);
               gentityInstance.setContainmentComplete("CLINICAL_DATA",true);
               System.out.println("checkEntity1 End" + Eclinical_data.getName());
             }
             
             if(EAP_Packages != null)
             {
               System.out.println("checkEntity1 Start" + EAP_Packages.getName());
                 //continue ceating InterviewEntitInstance
                 
                 EAP_PackageInstance = new InterviewEntityInstance("EAP_Packages", "3");
                 EAP_PackageInstance.setStatus(InstanceStatus.ADD); 
                 EAP_PackageInstance.setContainmentParent(EAP_EntityInstance);
                 userData.addInstance(EAP_PackageInstance);
                 EAP_EntityInstance.setContainmentComplete("EAP_Packages",true);
               System.out.println("checkEntity1 End" + EAP_Packages.getName());
             } 
               if(EAP_Alerts != null)
               {
                 System.out.println("checkEntity2 Start"+ EAP_Alerts.getName());
                 EAP_AlertsInstance = new InterviewEntityInstance("EAP_Alerts", "4");
                 EAP_AlertsInstance.setStatus(InstanceStatus.ADD);
                 EAP_AlertsInstance.setContainmentParent(EAP_EntityInstance);
                 userData.addInstance(EAP_AlertsInstance);
                 EAP_EntityInstance.setContainmentComplete("EAP_Alerts",true);
                // clinical_data_map.put("1",entityInstance2);
                 System.out.println("checkEntity1 End" + EAP_Alerts.getName());
               }
             if(Emedical_test != null)
             {
               System.out.println("checkEntity1 Start" + Emedical_test.getName());
                 InterviewEntityInstance entityInstance = new InterviewEntityInstance("medical_test", "5");
                 entityInstance.setStatus(InstanceStatus.ADD); 
                 entityInstance.setContainmentParent(CLINICAL_DATAInstance);
                 userData.addInstance(entityInstance);
                 CLINICAL_DATAInstance.setContainmentComplete("medical_test",true);
               System.out.println("checkEntity1 End" + Emedical_test.getName());
             }
            
            //Creating EAP Package Entities   
             try {
               List EAP_Packages_list = EAP_Packages.getChildEntities();
               String entity_identifier = "1";
               int list_size = EAP_Packages_list.size();
               int num = 0;
                while(list_size > num){
                    Entity ean = (Entity)EAP_Packages_list.get(num);
                    if(ean != null)  {
                      System.out.println("EAP Child Start" + ean.getName());
                        InterviewEntityInstance entityInstance = new InterviewEntityInstance(ean.getName(), entity_identifier);
                        entityInstance.setStatus(InstanceStatus.ADD); 
                        entityInstance.setContainmentParent(EAP_PackageInstance);
                        userData.addInstance(entityInstance);
                        EAP_PackageInstance.setContainmentComplete(ean.getName(),true);
                      System.out.println("EAP Child End" + ean.getName());
                    }
                    num = num + 1;
                  
                }
             }
             catch(Exception e){
               System.out.println("Error in Setting EAP Package Child. " + e.getMessage());
               e.printStackTrace();
             }
               
               
             //Creating EAP Alerts Entities   
              try {
                List EAP_Alerts_list = EAP_Alerts.getChildEntities();
                String entity_identifier = "1";
                int list_size = EAP_Alerts_list.size();
                int num = 0;
                 while(list_size > num){
                     Entity ean = (Entity)EAP_Alerts_list.get(num);
                     if(ean != null)  {
                       System.out.println("EAP Child Start" + ean.getName());
                         InterviewEntityInstance entityInstance = new InterviewEntityInstance(ean.getName(), entity_identifier);
                         entityInstance.setStatus(InstanceStatus.ADD); 
                         entityInstance.setContainmentParent(EAP_AlertsInstance);
                         userData.addInstance(entityInstance);
                         EAP_AlertsInstance.setContainmentComplete(ean.getName(),true);
                       System.out.println("EAP Child End" + ean.getName());
                     }
                     num = num + 1;
                   
                 }
              }
              catch(Exception e){
                System.out.println("Error in Setting EAP Package Child. " + e.getMessage());
                e.printStackTrace();
              }   
               
            
               
             try{  
  
               
               StringTokenizer st = new StringTokenizer(contactInfo, ";");
             
               if(st.countTokens() == 8) {
                   contact_id = st.nextToken();
                   first_name = st.nextToken();
                   last_name = st.nextToken();
                   age = st.nextToken();
                   sex = st.nextToken();
                   System.out.println(contact_id + "\t" +
                                      first_name + "\t" +
                                      last_name + "\t" +
                                      age + "\t" +
                                      sex);
                   
                 gentityInstance.setValue("case_id", contact_id);
                 
                 try{
                      first_name = first_name.replace('_',' ');
                      last_name = last_name.replace('_',' ');
                      gentityInstance.setValue("NC_name", first_name);
                      gentityInstance.setValue("NC_last_name", last_name);
                 }
                 catch(Exception e){
                   System.out.println("Error in Setting Name value. " + e.getMessage());
                   e.printStackTrace();
                 }                 
                 
                 
                 try{
                      gentityInstance.setValue("NC_age", Integer.parseInt(age));
                 }
                 catch(Exception e){
                   System.out.println("Error in Converting Age from String to Integer. " + e.getMessage());
                   e.printStackTrace();
                 }
                 
                 gentityInstance.setValue("NC_sex", sex);
               }
               else{                 
                   if(st.countTokens()==1) {
                       contact_id = st.nextToken();
                       System.out.println("Application Does not pass the Name, Age and Sex. ");
                       gentityInstance.setValue("case_id",contact_id);
                       System.out.println("Application Sets CaseID as : " + contact_id);
                   }                 
               }         
             
             }
             catch(Exception e){
               System.out.println("Parameter Initialization Error. ");
               System.out.println("Invalid Parameters.");
               System.out.println(e.getMessage());
               e.printStackTrace();
             } 
  
           }
           catch(Exception e){
             System.out.println("error " + e.getMessage());
             e.printStackTrace();
           }
        
        
      }
      catch(Exception e){
        System.out.println("error " + e.getMessage());
        e.printStackTrace();
      }      
      return userData;
    }

    /**
      * Saves the interview session by mapping entities, instances and attributes
      * to fields of tables
      */
    public String save(SecurityToken token, String caseID,
            InterviewSession session) {
        System.out.println("In Save");
        String generatedCaseID = caseID;
        return generatedCaseID;
    }

    /**
      * Registers the plugin using a specific rulebase
      */
    public InterviewSessionPlugin getInstance(InterviewSessionRegisterArgs args) {
      if (args.getSession().getRulebase().getIdentifier().equals(
              "Health Risk Assessment_US")) {
          return new HMPDataAdaptor();
      }
        if (args.getSession().getRulebase().getIdentifier().equals(
                "Health Risk Assessment")) {
            return new HMPDataAdaptor();
        }
        if (args.getSession().getRulebase().getIdentifier().equals(
                "Health Risk Assessment_SI")) {
            return new HMPDataAdaptor();
        }
      return null;       
    }
}
