package com.security.FraudData;

import java.io.File;
import java.util.Random;


import weka.core.Instances;
import weka.core.converters.CSVLoader;
import weka.core.converters.CSVSaver;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Remove;

public class CreateInstances 
{
	public static CSVLoader loader = new CSVLoader();
	
	public static  Instances getInstances(String path)
	{
		Instances thisInstance = null;
		try
		{
			loader.setSource(new File(path));
			loader.setNoHeaderRowPresent(true);
			thisInstance = loader.getDataSet();
			thisInstance.setClassIndex(0);
			
		}
	
		catch(Exception e)
		{
		
			System.out.println(e.getMessage());
		}
		return thisInstance;
		
	}
	public static Instances annonymize(Instances originalInstances)
	{
		String[] options = new String[2];
		options[0]="-R";
		options[1]="7";
		
		String[] options2 = new String[2];
		options2[0]="-R";
		options2[1]="25";
		//options[2]="26";
		Instances annoymisedInstances= null;
		Remove remove = new Remove();
		Remove remove2 = new Remove();
		
	
		try
		{
			
			remove.setOptions(options);
			remove.setInputFormat(originalInstances);
			annoymisedInstances = Filter.useFilter(originalInstances,remove);
			
			remove2.setOptions(options2);
			remove2.setInputFormat(annoymisedInstances);
			annoymisedInstances = Filter.useFilter(annoymisedInstances,remove2);
		}
		catch(Exception ext)
		{
			System.out.println("Thrown in annoymize "+ext.getMessage());
		}
		
		return annoymisedInstances;
	}
	public static void saveInCsv(Instances instances)
	{
		
		CSVSaver saver = new CSVSaver();
		try
		{
		saver.setNoHeaderRow(true);
		saver.setInstances(instances);
		saver.setFile(new File("/homehltuser/annonymisedRecords.csv"));
		saver.writeBatch();
		}
		catch(Exception exp)
		{
			System.out.println("Thrown in saveInsCSV");
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
