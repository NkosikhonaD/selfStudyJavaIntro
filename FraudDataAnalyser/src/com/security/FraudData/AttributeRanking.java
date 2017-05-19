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
		EM me = new EM();
		try
		{
			String[] opt = new String[2];
			String[] options =new String[4];
			options[0]= "-N";
			options[1]= "5";
			options[2]= "-S";
			options[3]= "10";
			opt[0]="-N";
			opt[1]="2";
			me.setOptions(options);
			//clusterer.setOptions(opt);
			//clusterer.buildClusterer(train);
			me.buildClusterer(train);
			System.out.println("# - cluster - distribution");
			for (int i = 0; i < test.numInstances(); i++)
			{
				//ClusterEvaluation eval = new ClusterEvaluation();
				//eval.setClusterer(me);
				int cluster = clusterer.clusterInstance(test.instance(i));
				//double[]  dist = clusterer.distributionForInstance(test.instance(i));
				int cluster_me = me.clusterInstance(test.instance(i));
				double[] dist_me = me.distributionForInstance(test.instance(i));
				System.out.print((i+1));
				System.out.print(" - ");
				//System.out.print(cluster_me);
				System.out.print(cluster);
				System.out.print(" - ");
				//System.out.print(Utils.arrayToString(dist));
				System.out.print(Utils.arrayToString(dist_me));
				//System.out.println(eval.clusterResultsToString());
				System.out.print(" - ");
				//System.out.print(test.instance(i));
				System.out.println();
			}
			
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
		    eval.evaluateClusterer(new Instances(test));
		    System.out.println("Log likelyhood "+eval.getLogLikelihood());
		    System.out.println(eval.clusterResultsToString());
		    
	    }
		 catch(Exception exp)
		{
			 System.out.println(exp.getMessage()); 
		}
	}
	
}

