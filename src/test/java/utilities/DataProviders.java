package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {

	//Dataprovider 1
	 
	@DataProvider(name ="LoginData")
	public String[][] getData() throws IOException
	{
		String path = ".\\testData\\OpenCart1_LoginData.xlsx"; //taking xl file from testData
		
		ExcelUtility xlutil = new ExcelUtility(path); // creating an Object for xlUtility
		
		int totalrows = xlutil.getRowCount("Sheet1");
		int totalcols = xlutil.getCellCount("Sheet1", 1);
		
		String logindata[][] =new String[totalrows][totalcols]; //created for two dimensional array which can store data
		
		for(int i=1;i<=totalrows;i++)   //1 ignoring the header row   //read the data from xl storing in two dimensional array
		{
			for(int j=0;j<totalcols;j++)   //0    i is rows,j is cols
			{
				logindata[i-1][j]=xlutil.getCellData("Sheet1", i, j);  //1,0  //i-1  bec array index starts from 0
			}
		}
		return logindata;  // returning two dimensional array
	}
	
	// DataProvider 2
	
	//DataProvider 3
	
	
}
