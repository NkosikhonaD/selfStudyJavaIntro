package com.security.FraudData;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import weka.associations.*;
import weka.classifiers.Classifier;
import weka.classifiers.functions.SMO;
import weka.classifiers.functions.supportVector.RBFKernel;
import weka.core.Instance;
import weka.core.Instances;
import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.evaluation.EvaluationUtils;
import weka.classifiers.evaluation.Prediction;
import weka.classifiers.trees.J48;
import weka.classifiers.trees.j48.*;

import java.util.Random;

import weka.core.converters.CSVLoader;
import weka.core.converters.ConverterUtils.DataSource;
public class FraudDataAnalyser
{
	
    ////private static final String ChronoUnits = null;
	public static String claimIndicator ="True";
	public static LocalDate today = LocalDate.now();
	public static LocalDate dateLoss=LocalDate.now();
	public static LocalDate dateClaim =LocalDate.now();
	public static LocalDate dateBirth =LocalDate.now();
	public static LocalDate dateStart =LocalDate.now();
	public static LocalDate dateEnd= LocalDate.now();
	public static int day,month,year,yearBirth,yearPolicyStart,yearPolicyEnd,yearDamage,yearClaim =0;
	public static int sumInsured,sumRevenue,amountPaid =0;
	
	public static int provinceIndex =1;
	public static int placeIndex =1;
	public static int cityIndex = 1;
	public static int indexStreets = 1;
	public static int indexLoss = 1;
	
	public static int provinceIndex1 =1;
	public static int placeIndex1 =1;
	public static int cityIndex1 = 1;
	
	
	public static int otherName =1;
	public static int indexCompay = 1;
	public static int indexPeople = 1;
	public static int indexGender =1;
	
	public static String[] peopleNames= {"John Yarnall","Deirdre Bilski","Deirdre Bilski","Alethea Haven","Jutta Richie","Suellen Mero","Cedric Inouye ","Olevia Racette","Chantel Zirbel  ","Collene Dumais  ","Amiee Bessler","Betsy Lorraine","Dulce Canez ","Amada Almada","Collen Dike","Margareta Obert","Lezlie Krach","Lurlene Slinkard ","Rigoberto Mercado","Yoshiko Avilez  ","Yoshiko Avilez","Ranee Yokley","Aja Tarpley","atum Bamber ","Tawana Metzinger","Jolene Bosworth","Caitlyn Day","Alisa Walling","Creola Waite","Anna Bankhead","Zona Deroche ","Rina Lynch  ","Shondra Sung ","Armanda Wozniak  ","Harris Mcchesney ","Heriberto Langston ","Jaqueline Schunk","Maurita Musselwhite","Gigi Zwart","Harley Farmer","Winona Hewey","Gonzalo Holtzen","Nelda Boateng","Sanford Schubert","Samuel Squier","Ezequiel Montesino","Cordie Manners","January Raborn","Shauna Genovese","Cecile Aichele","Ramiro Mungia","Eli Byerly"};
	public static String[] places = {"Vaal de Grace","Willow Park","Menlo Park","Meyers Park","Mooikloof","Erasmusrand","Fearie Glen","Lynwood Manor","Lynwood Ridge","Gugulethu","Willow Park","Rondebosch","Reitkol","Sundra","Hazyview","Malelane","uMlazi","Howick","Osizweni","Madadeni"};
	public static String[] cities	={"Johanesburg","Pretoria","Kimberly","Durban","Nelspriut","Polokwane","RustenBurg","Bloemfontien"};
	public static String[] towns  = {"Springs","WitBank","Delmas","Pongola","Kempton Park","Springs","Thabazimbi","Tzaneen","Palaborwa","Middleburg","NewCastle","Thohoyando"};
	public static String[] streets ={"Prospect Str","Hill Str","Hilda Aveune","Church","Standza","University Rd","Old Johannesburg Road", "Steve Biko Rd", "Kilimanjaro Street", "University Rd","Old Pretoria Rd", "Esselen","Lilian Ngoyi","Lynwood Rd","Bel Air Avenue"};
	public static String[] companyNames ={"Volvo car repairs "," Super Cars dealer ","BMW ","Rubex constructors "};
	public static String[] provinces ={"Gauteng ","Limpopo ","Mpumalana ","Western Cape ","Northen Cape ","Eastern Cape ","KwaZulu Natal ","Free State ","North West "};
	
	public static String[] gender= {"Male","Female"};
	public static String[] maritalStatus ={"Married","Single","Divorced","Widowed"};
	public static String[] loss = {"Fire","Theft","Storm","Highjack","Accident"};
	

	/*Generate IDs,
	 * 
	 */
	public static String getIDs()
	{
		return ""+randBetween(10000,50000)+","+randBetween(60000,80000);
	}
	public static String getGender(String[] g)
	{
		int index = randBetween(0,g.length-1);
		return g[index];
	}
	
	public static void generateData(String FileName)
	{
		String content="";
			
			try (BufferedWriter bw = new BufferedWriter(new FileWriter(FileName,true)))
			{
				System.out.println("Writting to file .....\n ");
				for( int i =0; i<100000;i++)
				{
				
					content= getAll(maritalStatus,gender,peopleNames,places,cities,towns,loss,streets,companyNames,provinces,i,0)+"\n";
					bw.write(content);
				}
			
				System.out.println("Done");
			} 
			catch (IOException e) 
			{

				e.printStackTrace();

			}
		
	}
	public static String getMaritalStatus( String[] s)
	{
		int i =  randBetween(0,s.length-1);
		return s[i]; 
	}
	public static String getAll(String[] marital, String[] gender, String peopleNames[],String places[],String[] cities,String[] towns,String[] loss ,String[] streets,String[] companyNames,String[] provinces,int position,int count)
	{
		
		sumRevenue =randBetween(1000,200000);
		day = randBetween(1,10);
		month=randBetween(1,5);
		yearBirth =randBetween(18,50);
		yearPolicyStart =yearBirth+randBetween(0,9);
		yearPolicyEnd =yearPolicyStart+randBetween(1,9);
		yearClaim = yearPolicyStart +randBetween(1,9);
		sumInsured =randBetween(20000,100000);
		provinceIndex =randBetween(0,provinces.length-1);
		placeIndex =randBetween(0,places.length-1);
		cityIndex = randBetween(0,cities.length-1);
		indexStreets = randBetween(0,streets.length-1);
		indexLoss = randBetween(0,loss.length-1);
		
		provinceIndex1 =randBetween(0,provinces.length-1);
		placeIndex1 =randBetween(0,places.length-1);
		cityIndex1 = randBetween(0,cities.length-1);
		amountPaid =getValidAmountPaid(sumInsured);
		
		otherName =randBetween(0,peopleNames.length-1);
		indexCompay = randBetween(0,companyNames.length-1);
		indexPeople = randBetween(0, peopleNames.length-1);
		indexGender =randBetween(0,gender.length-1);
		
		try
		{	
			dateBirth =today.minus(yearBirth,ChronoUnit.YEARS);
			dateBirth =dateBirth.minus(day,ChronoUnit.DAYS);
			dateBirth =dateBirth.minus(month,ChronoUnit.MONTHS);
			dateStart = dateBirth.plus(yearBirth,ChronoUnit.YEARS);
			dateStart = dateBirth.plus(day,ChronoUnit.DAYS);
			dateStart = dateBirth.plus(month,ChronoUnit.MONTHS);
			dateClaim = getValidClaimDate(dateStart,dateEnd);
			dateLoss = getValidDateLoss(dateStart,dateClaim);
		}
		catch(DateTimeException dte)
		{
			System.out.println(dte.getMessage());
		}
		catch(Exception exp)
		{
			System.out.println(exp.getMessage());
		}
		
		String record= ","+ dateLoss.toString() +","+ dateClaim.toString()+","+getIDs(); 
		//create Fraud entries
		if(position%1000==0)
		{
			count++;
			//change sumRevenue to ==0,
			if(count<20)
			{
				sumRevenue =0;
			record = "False"+","+"No revenue recieved"+ record +","+peopleNames[indexPeople]+","+gender[indexGender]+","+loss[indexLoss]+","+streets[indexStreets]+ ","+provinces[provinceIndex]+
					","+cities[cityIndex]+","+places[placeIndex]+","+getPostalCode()+","+provinces[provinceIndex1]+","+cities[cityIndex1]
						+","+places[placeIndex1]+","+getPostalCode()+","+ getMaritalStatus(marital)+","+dateBirth.toString()+","
						+"R"+sumInsured+","+"R"+sumRevenue+","+"R"+amountPaid+","+dateStart.toString()+","+dateEnd.toString()+","+peopleNames[otherName]+","+companyNames[indexCompay]+" "+cities[cityIndex];
			return record;
			}
			else if (count>=20 &&count <40)
			{
				amountPaid = sumInsured+amountPaid;
				record = "False"+","+"amount paid is greater than amount insured"+ record +","+peopleNames[indexPeople]+","+gender[indexGender]+","+loss[indexLoss]+","+streets[indexStreets]+ ","+provinces[provinceIndex]+
						","+cities[cityIndex]+","+places[placeIndex]+","+getPostalCode()+","+provinces[provinceIndex1]+","+cities[cityIndex1]
							+","+places[placeIndex1]+","+getPostalCode()+","+ getMaritalStatus(marital)+","+dateBirth.toString()+","
							+"R"+sumInsured+","+"R"+sumRevenue+","+"R"+amountPaid+","+dateStart.toString()+","+dateEnd.toString()+","+peopleNames[otherName]+","+companyNames[indexCompay]+" "+cities[cityIndex];
				return record;
			}
			else if (count >=40 && count<60)
			{
				
				
					record = "False"+","+"no loss"+ record +","+peopleNames[indexPeople]+","+gender[indexGender]+","+"none"+","+streets[indexStreets]+ ","+provinces[provinceIndex]+
							","+cities[cityIndex]+","+places[placeIndex]+","+getPostalCode()+","+provinces[provinceIndex1]+","+cities[cityIndex1]
								+","+places[placeIndex1]+","+getPostalCode()+","+ getMaritalStatus(marital)+","+dateBirth.toString()+","
								+"R"+sumInsured+","+"R"+sumRevenue+","+"R"+amountPaid+","+dateStart.toString()+","+dateEnd.toString()+","+peopleNames[otherName]+","+companyNames[indexCompay]+" "+cities[cityIndex];
			
					return record;
			}	
				//default to 
			else
			{
					record = "False"+","+"all entries are valid"+record +","+peopleNames[indexPeople]+","+gender[indexGender]+","+loss[indexLoss]+","+streets[indexStreets]+ ","+provinces[provinceIndex]+
							","+cities[cityIndex]+","+places[placeIndex]+","+getPostalCode()+","+provinces[provinceIndex1]+","+cities[cityIndex1]
								+","+places[placeIndex1]+","+getPostalCode()+","+ getMaritalStatus(marital)+","+dateBirth.toString()+","
								+"R"+sumInsured+","+"R"+0+","+"R"+amountPaid+","+dateStart.toString()+","+dateEnd.toString()+","+peopleNames[otherName]+","+companyNames[indexCompay]+" "+cities[cityIndex];
			
			
					return record;
			}	
			
		}
		else
		{
			record = "True"+","+"all entries are valid"+record +","+peopleNames[indexPeople]+","+gender[indexGender]+","+loss[indexLoss]+","+streets[indexStreets]+ ","+provinces[provinceIndex]+
			","+cities[cityIndex]+","+places[placeIndex]+","+getPostalCode()+","+provinces[provinceIndex1]+","+cities[cityIndex1]
				+","+places[placeIndex1]+","+getPostalCode()+","+ getMaritalStatus(marital)+","+dateBirth.toString()+","
				+"R"+sumInsured+","+"R"+sumRevenue+","+"R"+amountPaid+","+dateStart.toString()+","+dateEnd.toString()+","+peopleNames[otherName]+","+companyNames[indexCompay]+" "+cities[cityIndex];
			return record;
		} 
	}
	public static String getPostalCode()
	{
		return ""+randBetween(100,1000);
	}
	public static int getValidAmountPaid(int amountInsured)
	{
		return (randBetween(0,amountInsured));
	}
	
	public static LocalDate getValidDateLoss(LocalDate startDate, LocalDate claimDate)
	{
		boolean done =false;
		LocalDate temp = startDate;
		int changeItem = randBetween(1,4); //help us randomly choose which date field to change
		try
		{
			
			if(changeItem<2)
			{
				temp =temp.plus(changeItem,ChronoUnit.YEARS);
				temp =temp.plus(changeItem,ChronoUnit.DAYS);
			
			}
			else if(changeItem==2)
			{
				temp =temp.plus(changeItem,ChronoUnit.MONTHS);
				temp =temp.plus(changeItem,ChronoUnit.DAYS);
			}
			else 
			{
				temp =temp.plus(changeItem,ChronoUnit.MONTHS);
			}
			done = true;
		}
		catch(DateTimeException det)
			{
				
			}
		finally
		{
		if(!done)
			{
				getValidDateLoss(startDate,claimDate);
			}
		}
		
		return temp.isBefore(claimDate)? temp: claimDate; 
	}
	/*
	 * a claim can only be made after start date, or on end date.
	 * @param dateStart beginning of policy, 
	 * @param dateEnd date policy was terminated.
	 */
	
	public static LocalDate getValidClaimDate(LocalDate dateStart, LocalDate dateEnd)
	{
		boolean done = false;
		LocalDate temp = dateStart;
		int changeItem = randBetween(1,4);
		try
		{
			if(changeItem ==2)
			{
				temp = temp.plus(changeItem,ChronoUnit.YEARS);
				
			}
			else if(changeItem <2)
			{
				temp = temp.plus(changeItem,ChronoUnit.DAYS);		
			}
			else if (changeItem ==3)
			{
				temp = temp.plus(changeItem,ChronoUnit.MONTHS);
			}
			else 
			{
				temp = temp.plus(changeItem,ChronoUnit.YEARS);
				temp = temp.plus(changeItem,ChronoUnit.DAYS);
				temp = temp.plus(changeItem,ChronoUnit.MONTHS);
				
			}
			done = true;
			
		}
		catch(DateTimeException dte)
		{
		
		}
		finally 
		{
			if(!done)
			{
				getValidClaimDate(dateStart,dateEnd);
			}
		}
		
		return (temp.isBefore(dateEnd))? temp :dateEnd ;
	}
	public static void generateFraudInstances(String filename,String outputFile)
	{
		FileInputStream fstream ;
		  DataInputStream in ;
		  BufferedReader br;
		  String line;
		  BufferedWriter bw;
		  String content;
		  int trace = 0;
		try
		{
			
			fstream = new FileInputStream(filename);
			in = new DataInputStream(fstream);
			br = new BufferedReader(new InputStreamReader(in));
			bw= new BufferedWriter(new FileWriter(outputFile,true));
			System.out.println("Writting to file");
			while((line =br.readLine())!=null)
			{
				
				if(trace%1000==0)
				{
					content = line.replaceFirst("True","False\n");
					//System.out.println(content);
					
					bw.write(content);
					//break;
				}
				else
				{
					
					content = line;
					//System.out.println(content);
					bw.write(content+"\n");
				}
				
				trace++;
			
			}
			System.out.println("Done writting to file");


			in.close();
			bw.close();
		}
		catch(Exception exp)
		{
			System.out.println(exp.getMessage());
		}
		
		
		
	}
	/*
	 * random number generator between 2 numbers
	 * @ param start ,minimum int
	 * @ param int maximum int
	 */
	public static int randBetween(int start, int end)
	{
		return start +(int)Math.round(Math.random()*(end-start));
	}
	
	public static void trainNaiveBayesCrossValidateModel()
	{
		CSVLoader loader = new CSVLoader();
		
		try
		{
		 loader.setSource(new File("/home/hltuser/insuranceRecords.csv"));
			String[] option1s = new String[1]; 
			
			option1s[0] = "-H";
			loader.setOptions(option1s);
			Instances thisInstance = loader.getDataSet();
			thisInstance.setClassIndex(0);
			

		    // build associator
		    //Apriori apriori = new Apriori();
		    //apriori.setClassIndex(thisInstance.classIndex());
		    //apriori.buildAssociations(thisInstance);

		    // output associator
		   // System.out.println(apriori);
		
			NaiveBayes mynb = new NaiveBayes();
			//SMO mysmo = new SMO();
			//mynb.buildClassifier(thisInstance);
			Evaluation eval = new Evaluation(thisInstance);
			//eval.crossValidateModel(classifier, data, numFolds, random, forPredictionsPrinting);
	
			eval.crossValidateModel(mynb,thisInstance,10,new Random(1));
			
			//eval.evaluateModel(mySOM,thisInstance);
			System.out.println(eval.toSummaryString("Evaluation results:\n",false));
	
		}
		
		catch(Exception e)
		{
			
			System.out.println(e.getMessage());
		}
		
	}
	
	public static void trainJ48ValidateModel()
	{
		//CSVLoader loader1 = new CSVLoader();
		CSVLoader loader = new CSVLoader();
		
		try
		{
		 loader.setSource(new File("/home/hltuser/insuranceRecords.csv"));
		// loader1.setSource(new File("/home/hltuser/testAll.csv"));
			String[] option1s = new String[1]; 
			
			option1s[0] = "-H";
			loader.setOptions(option1s);
			
			Instances instances = loader.getDataSet();
			instances.setClassIndex(0);
			
			Instances randData = new Instances(instances);
			randData.randomize(new Random(1));
			randData.stratify(10);
			J48 j48 = new J48();
			NaiveBayes nb = new NaiveBayes();
			
			for(int i=0;i<10;i++)
			{
				Evaluation eval = new Evaluation(randData);
				
				Instances train = randData.trainCV(10,i);
				Instances test = randData.testCV(10,1);
				
				nb.buildClassifier(train);
				
				eval.evaluateModel(nb, test);
				System.out.println();
				System.out.println(eval.toMatrixString("=== Confusion matrix for fold " + (1+1) + "/" + 10 + " ===\n"));
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
			}
		}
		
		catch(Exception e)
		{
			
			System.out.println(e.getMessage());
		}
		
	}
	
	public static void trainModelClassifyInstance()
	{
		CSVLoader loader1 = new CSVLoader();
		CSVLoader loader = new CSVLoader();
		
		try
		{
		 loader.setSource(new File("/home/hltuser/train.csv"));
		 loader1.setSource(new File("/home/hltuser/testAll.csv"));
		 String[] option1s = new String[1]; 
			
			option1s[0] = "-H";
			//loader.setOptions(option1s);
			//loader1.setOptions(option1s);
			loader.setNoHeaderRowPresent(true);
			loader1.setNoHeaderRowPresent(true);
			Instances instancesTrain = loader.getDataSet();
			instancesTrain.setClassIndex(0);
			
			Instances instancesTest = loader1.getDataSet();
			instancesTest.setClassIndex(0);
			
		
			//J48 j48 = new J48();
			
			NaiveBayes nb = new NaiveBayes();
			
			nb.buildClassifier(instancesTrain);
			System.out.println("Actual Class, nb Predicted");
			for (int i = 0; i < instancesTest.numInstances(); i++) {
				//get class double value for current instance
				double actualValue = instancesTest.instance(i).classValue();

				//get Instance object of current instance
				Instance newInst = instancesTest.instance(i);
				//call classifyInstance, which returns a double value for the class
				double predSMO = nb.classifyInstance(newInst);
				
				//System.out.println(actualValue+", "+predSMO);
				System.out.println(instancesTest.instance(i).toString() +predSMO + " -> " + instancesTest.instance(i).classAttribute().value((int) predSMO));
	}
			
			
			
			
		}
		
		catch(Exception e)
		{
			
			System.out.println(e.getMessage());
		}
		
	}
	
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
	
		
		
		//generateData("/home/hltuser/insuranceRecords.csv");
		
		//trainNaiveBayesCrossValidateModel();
		//trainJ48ValidateModel();
		//generateFraudInstances("/home/hltuser/insuranceRecords.csv","/home/hltuser/insuranceRecordsFraud.csv");
		trainModelClassifyInstance();
		
		
	}

}
