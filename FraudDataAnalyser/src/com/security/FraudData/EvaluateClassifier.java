package com.security.FraudData;
import java.util.Random;
import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.core.Instance;
import weka.core.Instances;


public class EvaluateClassifier 
{
	//static Classifier myClassfier = null;
	////static Instances currentInstances = TrainClassifiers.getClassifierInstances();
	///static Instances trainInstances = TrainClassifiers.getClassfierTrainInstances();
	////static Instances testInstances = TrainClassifiers.getClassfierTestInstances();
	
	//System.out.println();
	static String confusionMatrix =" "; 
	static String Correct  ="";
	static String InCorrect  ="";
	static String auc ="";
	static String kappa ="";
	static String MAE = " ";
	static String RMSE = " ";
	static String precision = "";
	static String recall = " ";
	static String fMeasure = "";
	static String errorRate = "";
	static String allStats = "";
	public static void crossValidateClassifier(Instances currentInstances,Classifier myClassifier)
	{
		try
		{
			//myClassfier = TrainClassifiers.trainClassifer(currentInstances,"nb");
			Evaluation myEval = new Evaluation(currentInstances);
			myEval.crossValidateModel(myClassifier,currentInstances,10,new Random(1));
			//eval.evaluateModel(mySOM,thisInstance);
			System.out.println(myEval.toSummaryString("Evaluation results:\n",false));
			
		}
		catch(Exception ex)
		{
			System.out.println("Thrown in Evaluation "+ex.getMessage());
		}
		
	}
	
	/* 
	 * Train a classifier and evaluate 10 folds 
	 * @param classfierName give an name of the classifier use "nb" default
	 *
	 */
	public static void foldsEvaluation(Instances currentInstances,String classfierName,int folds)
	{			
				try
				{
				
					Instances instances = currentInstances;
					instances.setClassIndex(0);
					
					Instances randData = new Instances(instances);
					randData.randomize(new Random(1));
					randData.stratify(folds);
				
					for(int i=0;i<folds;i++)
					{
						Evaluation eval = new Evaluation(randData);
						
						Instances train = randData.trainCV(folds,i);
						Instances test = randData.testCV(folds,i);
						Classifier thisClassfier =TrainClassifiers.trainClassifer(train,classfierName);
						thisClassfier.buildClassifier(train);
						 confusionMatrix =""; 
						 Correct  ="Correct % =";
						 InCorrect  ="Incorrect % =";
						 auc ="AUC = ";
						 kappa ="kappa = ";
						 MAE = "MAE  = ";
						 RMSE = "RMSE = ";
						 precision = "Precision = ";
						 recall = "Recall = ";
						 fMeasure = "fMeasure = ";
						 errorRate = "Error Rate = ";
						eval.evaluateModel(thisClassfier, test);
						System.out.println();
						
						confusionMatrix = eval.toMatrixString("=== Confusion matrix for fold " + (1+i) + "/" + 10 + " ===\n");
						Correct=Correct+""+eval.pctCorrect()+"\n";
						InCorrect = InCorrect+""+eval.pctIncorrect()+"\n";
						auc= auc+""+eval.areaUnderROC(1)+"\n";
						kappa = ""+eval.kappa()+"\n";
						MAE = ""+eval.meanAbsoluteError()+"\n";
						RMSE="RMSE = "+eval.rootMeanSquaredError()+"\n";
						//System.out.println("RAE = "+eval.relativeAbsoluteError());
						//System.out.println("RRSE = "+eval.rootRelativeSquaredError());
						precision=precision+""+eval.precision(1)+"\n";
						recall="Recall = "+eval.recall(1)+"\n";
						fMeasure= "fMeasure = "+eval.fMeasure(1)+"\n";
						errorRate ="Error Rate = "+eval.errorRate()+"\n";
					
						System.out.println(eval.toMatrixString("=== Confusion matrix for fold " + (1+i) + "/" + 10 + " ===\n"));
						System.out.println("Correct % = "+eval.pctCorrect());
						System.out.println("Incorrect % = "+eval.pctIncorrect());
						System.out.println("AUC = "+eval.areaUnderROC(1));
						System.out.println("kappa = "+eval.kappa());
						System.out.println("MAE = "+eval.meanAbsoluteError());
						System.out.println("RMSE = "+eval.rootMeanSquaredError());
						System.out.println("RAE = "+eval.relativeAbsoluteError());
						System.out.println("RRSE = "+eval.rootRelativeSquaredError());
						System.out.println("Precision = "+eval.precision(1));
						System.out.println("Recall = "+eval.recall(1));
						System.out.println("fMeasure = "+eval.fMeasure(1));
						System.out.println("Error Rate = "+eval.errorRate());
						allStats=allStats+confusionMatrix+Correct+InCorrect+auc+kappa+MAE+RMSE+precision+recall+fMeasure+errorRate;
						 InCorrect  ="Incorrect % =";
						 auc ="AUC = ";
						 kappa ="kappa = ";
						 MAE = "MAE  = ";
						 RMSE = "RMSE = ";
						 precision = "Precision = ";
						 recall = "Recall = ";
						 fMeasure = "fMeasure = ";
						 errorRate = "Error Rate = ";
					}
				}
				
				catch(Exception e)
				{
					
					System.out.println(e.getMessage());
				}
		
	}
	
	/*
	 * evaluate that prints events in the  and prints instance and predicted class.
	*/
	public static void classifyInstance(Instances instancesTest, Classifier classifier)
	{
		for (int i = 0; i < instancesTest.numInstances(); i++) 
		{
			//get class double value for current instance
			double actualValue = instancesTest.instance(i).classValue();

			//get Instance object of current instance
			Instance newInst = instancesTest.instance(i);
			//call classifyInstance, which returns a double value for the class
			double predicted = 10.0;
			try 
			{
				predicted = classifier.classifyInstance(newInst);
			} 
			catch (Exception e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//System.out.println(actualValue+" , "+predSMO);
			System.out.println(instancesTest.instance(i).toString() +predicted + " -> " + instancesTest.instance(i).classAttribute().value((int) predicted));
		}
		
	}
	public static void main(String[] args)
	{
		
		
	}
			

}
