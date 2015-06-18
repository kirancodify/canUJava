import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import javax.jms.QueueReceiver;
import javax.jms.TextMessage;
import <your_java_websocket_WebSocket_library>;
import <your_java_websocket_handshake_ClientHandshake_library>;
import <your_java_websocket_server_WebSocketServer_library>;
import data.DrillSimulator;

/**
 * A simple DrillingServer implementation. Simulate real time drilling.
 */
public class DrillingServer extends WebSocketServer {

    private static String refreshVar;
    QueueReceiver queueReceiver = null;
    QueueReceiver filteredQueueReceiver = null;

    private static String DBusername = null;
    private static String DBpassword = null;

    private static String WLusername = null;
    private static String WLpassword = null;

	private DrillSimulator _simulator = new DrillSimulator(DBusername,DBpassword,WLusername,WLpassword);


	public DrillingServer( int port ) throws UnknownHostException {
		super( new InetSocketAddress( port ) );
		initializeTimerEvent();
	}

	public DrillingServer( InetSocketAddress address ) {
		super( address );
		initializeTimerEvent();
	}

	Timer timer;
	private void initializeTimerEvent(){
		timer = new Timer();

		data.JMSConnectionClass queueConn = new data.JMSConnectionClass();

		queueReceiver = queueConn.getQueueReceiver("JMS_QUEUE_NAME_TO_RECEIVE_RAW_DATA",WLusername,WLpassword);

		filteredQueueReceiver = queueConn.getQueueReceiver("JMS_QUEUE_NAME_TO_RECEIVE_CEP_DATA",WLusername,WLpassword);

		timer.schedule(new TimerTask(){

			@Override
			public void run() {

				synchronized (DrillingServer.this) {
					DrillingServer.this.processEvent();
				}

			}
		}, 1000, 2000);
		try{

		}
		catch(Exception e){
			e.printStackTrace();
		}
	}


	Date _lastAlarmDate = new Date();
	protected void processEvent(){
		try{

			if(connections().size() == 0)
				return;

			if((new Date()).getTime()-_lastAlarmDate.getTime()>2*1000){
				_lastAlarmDate = new Date();

				String alarm = null;
				if(alarm!=null)
					sendToAll(alarm);
			}
			TextMessage textMessage = null;

	        while ((textMessage = (TextMessage) queueReceiver.receive(1)) != null) {

	            if(textMessage.getText() != null){
		            String xml = textMessage.getText();

		    		if((xml.indexOf("<refreshINT>")) != -1){
		    			Boolean val = _simulator.historyData.clearDataArray();

		    		}
		    		else {

			            String message = null;
			            if(refreshVar.equalsIgnoreCase("New")){

					        message = _simulator.getCurrentStatePackage(xml,DBusername,DBpassword);
					        	sendToAll(message);
					        	refreshVar = "Old";
			            }else{
					        message = _simulator.getCurrentDepthPackage(xml);
					        String data[] = message.split("//");
					        String uom = data[0];
					        for(int k=1;k<data.length;k++){
					        	wait(30);
					        	String output = uom + "\n" + data[k];
					        	sendToAll(output);
					        }


			            }
		            }

	            }
	        }

	        TextMessage txtMessage = null;

	        while ((txtMessage = (TextMessage) filteredQueueReceiver.receive(1)) != null) {

	        	if(txtMessage.getText() != null){
	        		String xml2 = txtMessage.getText();
	        		String filterMessage = _simulator.getFilteredData(xml2);

	        		sendToAll(filterMessage);

	        	}

	        }

		}
		catch(Exception e){
			e.printStackTrace();
		}

	}

	@Override
	public void onOpen( WebSocket conn, ClientHandshake handshake ) {
		synchronized (DrillingServer.this){
			try{

				refreshVar = "New";
				TextMessage textMessage = (TextMessage) queueReceiver.receive(1);
				if(textMessage != null){
					String xml = textMessage.getText();

		    		if((xml.indexOf("<refreshINT>")) != -1){
		    			Boolean val = _simulator.historyData.clearDataArray();

		    		}
		    		else {
						conn.send(_simulator.getCurrentStatePackage(xml,DBusername,DBpassword));

						refreshVar = "Old";
		    		}
				}
				else if((data.DataSaveClass.dataArray != null) && (data.DataSaveClass.dataArray.size()> 0)){
					conn.send(_simulator.getCurrentStatePackage("SHOW_HISTORY_DATA",DBusername,DBpassword));

					refreshVar = "Old";
				}
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
	}

	@Override
	public void onClose( WebSocket conn, int code, String reason, boolean remote ) {
		System.out.println( conn + " has been closed!" );

	}

	@Override
	public void onMessage( WebSocket conn, String message ) {

	}

	public static void main( String[] args ) throws InterruptedException , IOException {
		WebSocket.DEBUG = false;
		int port = PORT_NUMBER_TO_RECEIVE_DATA;
		try {

			DBusername = args[0];
		    DBpassword = args[1];

		    WLusername = args[2];
		    WLpassword = args[3];

		} catch ( Exception ex ) {
			ex.printStackTrace();
		}
		DrillingServer s = new DrillingServer( port );
		s.start();

		BufferedReader sysin = new BufferedReader( new InputStreamReader( System.in ) );
		while ( true ) {
			String in = sysin.readLine();
			if(in == "exit")
				return;

		}
	}

	@Override
	public void onError( WebSocket conn, Exception ex ) {
		ex.printStackTrace();
	}

	/**
	 * Sends <var>text</var> to all currently connected WebSocket clients.
	 *
	 * @param text
	 *            The String to send across the network.
	 * @throws InterruptedException
	 *             When socket related I/O errors occur.
	 */
	public void sendToAll( String text ) {
		Set<WebSocket> con = connections();
		synchronized ( con ) {
			for( WebSocket c : con ) {
				c.send( text );
			}
		}
	}
}
