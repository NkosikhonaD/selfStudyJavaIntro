package com.security.FraudData;

import weka.classifiers.Classifier;
import weka.classifiers.bayes.NaiveBayes;
import weka.core.Instances;

public class TrainClassifiers
{
	
	static Instances instances  = CreateInstances.getInstances("/home/hltuser/insuranceRecords.csv");
	static Instances instancesTrain  = CreateInstances.getInstances("/home/hltuser/train.csv");
	static Instances instancesTest  = CreateInstances.getInstances("/home/hltuser/testAll.csv");
	
	public static Classifier trainClassifer(Instances instances,String classifierName)
	{
		Classifier classifier = null;
		if(classifierName.equalsIgnoreCase("nb"))
		{
			classifier = new NaiveBayes();
			try
			{
				classifier.buildClassifier(instances);
			}
			catch(Exception exp)
			{
				System.out.println(exp.getMessage());
			}
				
		}
		return classifier;
	}
	
	public static Classifier trainClassfierTrainSet(Instances instances,String classifierName)
	{
		
		Classifier classifier = null;
		if(classifierName.equalsIgnoreCase("nb"))
		{
			classifier = new NaiveBayes();
			try
			{
				classifier.buildClassifier(instances);
			}
			catch(Exception exp)
			{
				System.out.println(exp.getMessage());
			}
				
		}
		return classifier;
		
	}
	//get instances used to train the classifier
	public static Instances getClassifierInstances()
	{
		return TrainClassifiers.instances;
	}
	public static Instances getClassfierTrainInstances()
	{
		return TrainClassifiers.instancesTrain;
	}
	public static Instances getClassfierTestInstances()
	{
		return TrainClassifiers.instancesTest;
	}
	
	
	
}
