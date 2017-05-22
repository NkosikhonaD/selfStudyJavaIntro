package com.security.FraudData;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class CleanData 
{
	
	public static String update = "update";
	/*There are 27 attributes 
	 * data with less attributes must be removed
	 * @Param fileName of pointing to csv file that contains database  
	 */
	public static void cleanInstances(String filename,String outputFile)
	{
		  FileInputStream fstream ;
		  DataInputStream in ;
		  BufferedReader br;
		  String line;
		  BufferedWriter bw;
		  String content;
		try
		{
			
			fstream = new FileInputStream(filename);
			in = new DataInputStream(fstream);
			br = new BufferedReader(new InputStreamReader(in));
			bw= new BufferedWriter(new FileWriter(outputFile,true));
			update = "Cleaning data data ....\n";
			System.out.println(update);
			while((line =br.readLine())!=null)
			{
				if(!checkMissingAttributes(line))
				{
					content = line;
					bw.write(content+"\n");
				}
				else
				{
					update ="Found unclean data :"+line ;
					System.out.println(update);
				}
			}
			update = "Done cleaning and saving into file writting to file\n";
			System.out.println(update);
			in.close();
			bw.close();
		}
		catch(Exception exp)
		{
			System.out.println(exp.getMessage());
		}
	}
	
	public static void verifyCleanInstances(String filename)
	{
		  FileInputStream fstream ;
		  DataInputStream in ;
		  BufferedReader br;
		  String line;
		  BufferedWriter bw;
		  String content;
		  boolean count = true;
		try
		{
			
			fstream = new FileInputStream(filename);
			in = new DataInputStream(fstream);
			br = new BufferedReader(new InputStreamReader(in));
			update = "Verifying data ...\n";
			System.out.println(update);
			while((line =br.readLine())!=null)
			{
				if(checkMissingAttributes(line))
				{
					//content = line;
					update ="Found record with incomplete  attributes please go ahead and clean data\n Line:" +line+"\nLocation"+filename+"\n";
					System.out.println(update);
					in.close();
					count =false;
					break;
				}
			}
			if(count)
			{
				update = "Done checking all records have complete attributes\n";
				System.out.println("Done cheking file attributes all instances have complete attributes\n "+"File name "+filename);
			}
			//System.out.println("Done cheking file attributes all instances have complete attributes\n "+"File name "+filename);
			in.close();
		
		}
		catch(Exception exp)
		{
			System.out.println(exp.getMessage());
		}
	}
	
	
	public static boolean checkMissingAttributes(String line)
	{
		String[] arry = line.split(",");
		boolean t = false;
		for(String s : arry )
		{
			if(s==null)
			{
				t = true;
			}
			if(s.trim().length()<=1)
			{
				t=true;
			}
		}
	
		return t;
	}
	public boolean validPolicyStartDate(LocalDate birthDate, LocalDate policyStartDate)
	{
		boolean t = false;
		double time = ChronoUnit.YEARS.between(birthDate,policyStartDate);
		if(time >= 18)
		{
			t= true;
		}
		
		return t;
	}
	
	public static void main(String[] args)
	{
		//cleanInstances("/home/hltuser/newData1.csv","/home/hltuser/cleanNewData1.csv");
		//verifyCleanInstances("/home/hltuser/newData1.csv");
		//verifyCleanInstances("/home/hltuser/cleanNewData1.csv");
		
	}
	
	

}
