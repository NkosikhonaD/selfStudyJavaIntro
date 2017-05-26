package com.security.FraudData;

import java.io.File;

import weka.core.Attribute;
import weka.core.Instances;
import weka.core.converters.CSVLoader;
import weka.core.converters.CSVSaver;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Remove;

public class CreateInstances 
{
	public static CSVLoader loader = new CSVLoader();
	
	public static  Instances getInstances(String pathtocsv)
	{
		Instances thisInstances = null;
		try
		{
			loader.setSource(new File(pathtocsv));
			loader.setNoHeaderRowPresent(true);
			//System.out.println(loader.getStructure());
			thisInstances = loader.getDataSet();
			thisInstances.setClassIndex(0);
		}
	
		catch(Exception e)
		{
		
			System.out.println(e.getMessage());
		}
		return thisInstances;
		
	}
	public static  Instances getInstancesWithoutClass(String pathtocsv)
	{
		Instances thisInstances = null;
		try
		{
			loader.setSource(new File(pathtocsv));
			loader.setNoHeaderRowPresent(true);
			//System.out.println(loader.getStructure());
			thisInstances = loader.getDataSet();
		}
	
		catch(Exception e)
		{
		
			System.out.println(e.getMessage());
		}
		return thisInstances;
		
	}
	
	
	
	/*Remove identifiers and  sensitive information like marriage status.
	 * 
	 * remove attributes found in positon  7- policy hoders name
	 *                                     19 Marital status of the policy holder.
	 *                                     26 Name of third-party 
	 */
	public static Instances annonymize(Instances originalInstances)
	{
		String[] options = new String[2];
		options[0]="-R";
		options[1]="7,19,26";
		Instances annoymisedInstances= null;
		Remove remove = new Remove();

		try
		{
			remove.setOptions(options);
			remove.setInputFormat(originalInstances);
			annoymisedInstances = Filter.useFilter(originalInstances,remove);
		}
		catch(Exception ext)
		{
			System.out.println("Thrown in annoymize "+ext.getMessage());
		}
		return annoymisedInstances;
	}
	public static Instances generalize(Instances originalInstances)
	{
		Instances tempInstances = originalInstances;
		Attribute att = tempInstances.attribute(7);
		for(int n=0 ;n <att.numValues();n++)
		{
			tempInstances.renameAttributeValue(att,att.value(n),"people");
		}
		
		return annonymize(tempInstances);
	}
	
	
	
	public static void saveInCsv(Instances instances)
	{
		
		CSVSaver saver = new CSVSaver();
		try
		{
		saver.setNoHeaderRow(true);
		saver.setInstances(instances);
		System.out.println("Saving annonymised Records in file location /home/hltuser/runSimulation/annonymisedRecords.csv");
		saver.setFile(new File("/home/hltuser/runSimulation/annonymisedRecords.csv"));
		
		saver.writeBatch();
		}
		catch(Exception exp)
		{
			System.out.println("Thrown in saveInsCSV method of CreateInstance Class");
		}
	}
	public static void viewInstances(Instances instances, int total )
	{
		System.out.println("Number of instances "+instances.numInstances() );
		System.out.println("Number of attributes per instance "+instances.get(1).numAttributes());
		if (total >instances.numInstances())
		{
			
			total = instances.numInstances();
		}
		for(int i=0;i<total;i++)
		{
			System.out.println(instances.instance(i));
		}
		
	}
}
