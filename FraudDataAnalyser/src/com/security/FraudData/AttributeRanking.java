package com.security.FraudData;
import java.util.Random;

import weka.clusterers.ClusterEvaluation;
import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.clusterers.EM;
import weka.clusterers.SimpleKMeans;
import weka.core.Instances;
import weka.core.Utils;


public class AttributeRanking 
{
	
	
	
	public static void clustering(Instances train, Instances test)
	{
		SimpleKMeans clusterer = new SimpleKMeans();
		ClusterEvaluation eval = new ClusterEvaluation();
		try
		{
			
			String[] options =new String[4];
			options[0]= "-N";
			options[1]= "2";
			options[2]= "-S";
			options[3]= "10";
			String[] opt ={"-c","1"};
			clusterer.setOptions(options);
			//clusterer.setDisplayStdDevs(true);
			clusterer.buildClusterer(train);
			System.out.println();
			//eval.evaluateClusterer(train);
		
			System.out.println("# - cluster - distribution");
			
			for (int i = 0; i < test.numInstances(); i++)
			{
				int cluster = clusterer.clusterInstance(test.instance(i));
				
				double[] dist_me = clusterer.distributionForInstance(test.instance(i));
				
				clusterer.getSquaredError();
				System.out.print((i+1));
				System.out.print(" - ");
				System.out.print(cluster);
				System.out.print(" - ");
				System.out.print(Utils.arrayToString(dist_me));
				System.out.print(" - ");
				
			}
			System.out.println();
			//System.out.println(clusterer.getClusterStandardDevs());
			
	    }
		 catch(Exception exp)
		{
			 System.out.println(exp.getMessage()); 
		}
	}
	public static void clusteringCross(Instances train,Instances test)
	{
		SimpleKMeans clusterer = new SimpleKMeans();
		String[] options =new String[4];
		options[0]= "-N";
		options[1]= "2";
		options[2]= "-S";
		options[3]= "10";
		ClusterEvaluation eval = new ClusterEvaluation();
		
		try
		{
			clusterer.setOptions(options);
			clusterer.buildClusterer(train);
		    eval = new ClusterEvaluation();
		    eval.setClusterer(clusterer);
		   // clusterer.setDisplayStdDevs(true);
		    eval.evaluateClusterer(new Instances(test));
		    System.out.println(eval.clusterResultsToString());
		    //eval.getLogLikelihood();
		    
	    }
		 catch(Exception exp)
		{
			 System.out.println(exp.getMessage()); 
		}
	}
	public static void main(String[] args)
	{
		Instances trainIn = CreateInstances.getInstancesWithoutClass("/home/hltuser/Security/annymisedTrain.csv");
		Instances testIn= CreateInstances.getInstancesWithoutClass("/home/hltuser/Security/annymisedTest.csv");
		
		clusteringCross(trainIn,testIn);
	}
	
}

