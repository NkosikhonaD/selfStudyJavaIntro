package com.security.FraudData;

import java.io.File;
import java.util.Random;

import weka.core.Instances;
import weka.core.converters.CSVLoader;

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
}
