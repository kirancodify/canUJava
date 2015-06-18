package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;

public class DataSaveClass {
	private static String mnemonicArray = null;
	public static List<String> dataArray= new ArrayList<String>();

	private static int index = 0;

	private static String uom = "";
	
	public void addUOM(String units){
		uom = units;

	}
	
	public void addUOM(String mnList, String units){
		uom = "";

		String[] mnemArray = mnList.split(",");
		String[] valuesArray = units.split(",");
		String[] stdMnemonics = mnemonicArray.split(",");
		HashMap unitList= new HashMap();

		for(int k=0;k<mnemArray.length;k++){
			unitList.put(mnemArray[k], valuesArray[k]);
		}

		for(int i=0;i<stdMnemonics.length;i++){
			if(unitList.get(stdMnemonics[i]) != null){

				uom = uom + ((unitList.get(stdMnemonics[i])) + (i<stdMnemonics.length-1 ? "," : ""));

			}
			else{
				uom = uom + ("NaN"+ (i<stdMnemonics.length-1 ? "," : ""));
			}
		}

	}
	
	public String getUOM(){
		
		if(uom == null){
			uom = "m,klbf,m,m,kft.lbf,kft.lbf,m/h,klbf,rpm,rpm,kft.lbf,kft.lbf,kft.lbf,kft.lbf,galUS,galUS,g/cm3,degC,rpm,,g/cm3";
		}

		return uom;

	}

	public boolean clearDataArray(){

		try{
			dataArray.clear();
			return true;
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}

	}

	public void setMnemonicArray(String dbuname,String dbpwd){
		Connection connection = null;
		List mnArray = null;
		String mnStringArray = "";
		try {
		   
		    String driverName = "oracle.jdbc.driver.OracleDriver";
		    Class.forName(driverName);

		    
		    String serverName = "<SOA_SERVER_NAME>";
		    String portNumber = "<PORT>";
		    String service = "<SOA_SERVER_NAME>";
		    String url = "jdbc:oracle:thin:@//" + serverName + ":" + portNumber + "/" + service;
		    String username = dbuname;
		    String password = dbpwd;

		    connection = DriverManager.getConnection(url, username, password);

		    String query = "select mnemonic_key from S_WELL_KEY_MAP where UWI = 'W-128' order by mseq ASC";
		     Statement st = connection.createStatement();
		     ResultSet rs = st.executeQuery(query);


		     int i = 0;
		     mnArray = new ArrayList();
		     for (i = 0;rs.next();i++)
		      {

		        mnArray.add(rs.getString("mnemonic_key"));

		      }
		     for (i = 0;i<mnArray.size();i++)
		      {
		    	 mnStringArray = mnStringArray + mnArray.get(i);
		    	 if(i != (mnArray.size()-1)){
		    		 mnStringArray = mnStringArray + ",";
		    	 }
		      }


		} catch (Exception e) {
			e.printStackTrace();
		}

		mnemonicArray = mnStringArray;

	}


	public String getMnemonicArray(){
		return mnemonicArray;

	}

	public void addDataArray(String mnArray, String values){
		String[] stdMnemonics = mnemonicArray.split(",");
		HashMap stdPairValues = new HashMap();
		HashMap actualValues = new HashMap();
		StringBuilder dataAdd = new StringBuilder();
		try{
			String[] mnemonicArray = mnArray.split(",");
			String[] valuesArray = values.split(",");

			for(int k=0;k<mnemonicArray.length;k++){
				actualValues.put(mnemonicArray[k], valuesArray[k]);
			}

			for(int i=0;i<stdMnemonics.length;i++){
				if(actualValues.get(stdMnemonics[i]) != null){

					dataAdd.append((actualValues.get(stdMnemonics[i])) + (i<stdMnemonics.length-1 ? "," : ""));

    			}
    			else{
    				dataAdd.append("NaN"+ (i<stdMnemonics.length-1 ? "," : ""));
    			}
			}
			dataArray.add(dataAdd.toString());


		}
		catch(Exception e){
		}
	}

	public List<String> getDataArray(){
		if(dataArray != null){
		}
		return dataArray;
	}

}
