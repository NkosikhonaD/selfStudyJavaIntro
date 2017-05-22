package com.security.FraudData;

import weka.classifiers.Classifier;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.bayes.NaiveBayesMultinomialText;
import weka.classifiers.functions.SMO;
import weka.core.Instances;

public class TrainClassifiers
{
	
	//static Instances instances  = CreateInstances.getInstances("/home/hltuser/Security/annonymisedRecords.csv");
	//static Instances instancesTrain  = CreateInstances.getInstances("/home/hltuser/train.csv");
	//static Instances instancesTest  = CreateInstances.getInstances("/home/hltuser/testAll.csv");
	
	public static Classifier trainClassifer(Instances instances,String classifierName)
	{
		Classifier classifier = null;
		if(classifierName.equalsIgnoreCase("nb"))
		{
			classifier = new NaiveBayesMultinomialText();
			try
			{
				
				classifier.buildClassifier(instances);
			}
			catch(Exception exp)
			{
				System.out.println(exp.getMessage());
			}
				
		}
		if(classifierName.equalsIgnoreCase("svm"))
		{
			classifier = new SMO();
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
	public static void main(String[] args)
	{
		//Instances in = getClassifierInstances();
		//Classifier c = trainClassifer(in,"nb");
		//EvaluateClassifier.classifyInstance(in,c); --
		//EvaluateClassifier.crossValidateClassifier();-- works for naive bayes
		//EvaluateClassifier.foldsEvaluation("nb",10);---works
	}

}
