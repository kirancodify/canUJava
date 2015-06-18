package com.oracle.determinations.interview.engine.userplugins;

/**
 * 
 * @author      <a href="mailto:kiran.mo.kumar@oracle.com">Kiran Kumar</a>
 *
 */

import com.oracle.determinations.interview.engine.InterviewSession;
import com.oracle.determinations.interview.engine.SecurityToken;
import com.oracle.determinations.interview.engine.security.BasicSecurityToken;
import com.oracle.determinations.web.platform.controller.SessionContext;
import com.oracle.determinations.web.platform.eventmodel.events.OnGetScreenEvent;
import com.oracle.determinations.web.platform.eventmodel.events.OnInvestigationEndedEvent;
import com.oracle.determinations.web.platform.eventmodel.handlers.OnGetScreenEventHandler;
import com.oracle.determinations.web.platform.eventmodel.handlers.OnInvestigationEndedEventHandler;
import com.oracle.determinations.web.platform.plugins.PlatformSessionPlugin;
import com.oracle.determinations.web.platform.plugins.PlatformSessionRegisterArgs;
import com.oracle.determinations.interview.util.TypedInputStream;
import com.oracle.determinations.interview.engine.exceptions.DocumentGenerationServerException;
import com.oracle.determinations.interview.engine.exceptions.NullTemplateException;
import com.oracle.determinations.web.platform.exceptions.error.DocumentGenerationServerError;
import com.oracle.determinations.web.platform.exceptions.error.NullTemplateError;

public class AutoSaveTrigger implements  OnInvestigationEndedEventHandler //,OnGetScreenEventHandler
 {

    public AutoSaveTrigger()
    {
        //parameter-less constructor to satisfy Plugin requirement
    }

    //Triggers autosaving when an investigation of a goal finishes
    public void handleEvent(Object sender, OnInvestigationEndedEvent event) {
	System.out.println("handleEvent");
        SessionContext currentContext = event.getSessionContext();
		System.out.println("Got SessionContext");
        SecurityToken token = new BasicSecurityToken("");
		System.out.println("Got SecurityToken");
        InterviewSession currentSession = currentContext.getInterviewSession();
		System.out.println("Got currentSession");
        String caseID = currentContext.getCaseID();
		System.out.println("caseID"+caseID);
        //This calls the DataAdaptor save() when the investigation finishes, thus saving the user's answer to the last Question
        if(caseID != null)
        {
              TypedInputStream localTypedInputStream;
              TypedInputStream localTypedInputStream_xml;
              
              try { localTypedInputStream = currentSession.generateDocument("d1@Interviews_NewScreensFile_xint", false);
              } catch (NullTemplateException localNullTemplateException) {
                throw new NullTemplateError(localNullTemplateException.getDocumentName());
              } catch (DocumentGenerationServerException localDocumentGenerationServerException) {
                throw new DocumentGenerationServerError(localDocumentGenerationServerException.getServerUrl());
              }
        
              try { localTypedInputStream_xml = currentSession.generateDocument("d1@Interviews_NewScreensFile_xint", true);
              } catch (NullTemplateException localNullTemplateException) {
                throw new NullTemplateError(localNullTemplateException.getDocumentName());
              } catch (DocumentGenerationServerException localDocumentGenerationServerException) {
                throw new DocumentGenerationServerError(localDocumentGenerationServerException.getServerUrl());
              }
                    if(caseID != "")
                        currentContext.setCaseID(caseID);
        }
    }

    public PlatformSessionPlugin getInstance(PlatformSessionRegisterArgs args) {
        try{
        if (args.getContext().getInterviewSession().getRulebase().getIdentifier().equals(
                "Health Risk Assessment_US") &&
             args.getContext().getInterviewSession()!=null) {
            return new AutoSaveTrigger();
        }
        if (args.getContext().getInterviewSession().getRulebase().getIdentifier().equals(
                "Health Risk Assessment") &&
             args.getContext().getInterviewSession()!=null) {
            return new AutoSaveTrigger();
        }
        if (args.getContext().getInterviewSession().getRulebase().getIdentifier().equals(
                "Health Risk Assessment_SI") &&
             args.getContext().getInterviewSession()!=null) {
            return new AutoSaveTrigger();
        }
        else {
                  return null;
              }
        }
        
        catch(Exception e){
          return null;
        }
	
    }
}
