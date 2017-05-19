package com.security.FraudData;

import weka.core.Instances;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Normalize;

public class Scalling 
{
	
	public static Instances scaleInstances(Instances instances)
	{
		Normalize normalize = new Normalize();
		Instances processed_train=null;
		try
		{
			normalize.setInputFormat(instances);
			processed_train = Filter.useFilter(instances, normalize);
		}
		catch(Exception exp)
		{
			System.out.println("Thown in scaleInstances class Scalling "+exp.getMessage());
		}
		
		return processed_train;
	}
	

}
