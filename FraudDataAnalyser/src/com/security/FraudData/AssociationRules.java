package com.security.FraudData;

import weka.associations.Apriori;
import weka.core.Instances;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Discretize;

public class AssociationRules 
{
	
	public static Instances data = CreateInstances.getInstances("/home/hltuser/insuranceRecords.csv");
	
	public static void printAssociations()
	{
	  // build associator
		Apriori apriori = null;
		
		Instances discritizedInstances;
		try
		{
		  Discretize  filter =  new Discretize();
		  
		  apriori = new Apriori();
		  apriori.setClassIndex(data.classIndex());
		  apriori.buildAssociations(data);
		  // output associator
		  System.out.println(apriori);
		}
		catch(Exception exp)
		{
		  System.out.println(exp.getMessage());
		}
	}
	public static void main(String[] args)
	{
		
		printAssociations();
	}

}
