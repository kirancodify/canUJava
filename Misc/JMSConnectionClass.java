package data;

import java.util.Properties;

import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueReceiver;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import weblogic.jndi.WLInitialContextFactory;

public class JMSConnectionClass {

    private QueueConnectionFactory queueConnectionFactory = null;
    private Queue ringQueue = null;
    private Context jndiContext = null;
    private Properties properties = null;
    QueueConnection queueConnection = null;
    QueueSession queueSession = null;
    QueueReceiver queueReceiver = null;



	public JMSConnectionClass(){

	}

	public QueueReceiver getQueueReceiver(String queueName,String wlusername,String wlpassword){
		try {
			properties = new Properties();

        	properties.put(Context.INITIAL_CONTEXT_FACTORY,"weblogic.jndi.WLInitialContextFactory");
			properties.put(Context.PROVIDER_URL, "t3://<SOA_SERVER_NAME>:<PORT>");
			properties.put(Context.SECURITY_PRINCIPAL, wlusername);
			properties.put(Context.SECURITY_CREDENTIALS, wlpassword);

			try {

				jndiContext = new InitialContext(properties);


		       }
			catch (NamingException ne) {
		           ne.printStackTrace(System.err);
		           System.exit(0);
		       }
			try{

				queueConnectionFactory = (QueueConnectionFactory)jndiContext.lookup("JMS_QUEUE_CONNECTION_FACTORY_NAME");

			}
			catch(Exception e){
				e.printStackTrace();
			}

            ringQueue = (Queue) jndiContext.lookup(queueName);


        } catch (NamingException nameEx) {

        }

        try {
            queueConnection =
              queueConnectionFactory.createQueueConnection();
            queueSession =
              queueConnection.createQueueSession(
                false, Session.AUTO_ACKNOWLEDGE);
            queueReceiver =
              queueSession.createReceiver(ringQueue);
            queueConnection.start();


        } catch (javax.jms.JMSException jmsEx) {

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        try{

        }
        catch(Exception w){
        	w.printStackTrace();
        }

        return queueReceiver;
	}
}
