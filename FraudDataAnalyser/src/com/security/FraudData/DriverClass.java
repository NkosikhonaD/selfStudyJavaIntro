package com.security.FraudData;

import java.io.File;

import weka.core.Instances;
import weka.core.converters.CSVSaver;

public class DriverClass 
{
	public static void main(String[] args)
	{
		Instances currInst = CreateInstances.getInstances("/home/hltuser/insuranceRecords.csv");
		
		Instances annoymised = CreateInstances.annonymize(currInst);
		CreateInstances.viewInstances(annoymised, 10);
	}

}
