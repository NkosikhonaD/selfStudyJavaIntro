package com.security.FraudData;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import weka.associations.*;
import weka.classifiers.Classifier;
import weka.classifiers.functions.SMO;
import weka.core.Instances;
import weka.classifiers.Evaluation;
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
				
					content= getAll(maritalStatus,gender,peopleNames,places,cities,towns,loss,streets,companyNames,provinces)+"\n";
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
	public static String getAll(String[] marital, String[] gender, String peopleNames[],String places[],String[] cities,String[] towns,String[] loss ,String[] streets,String[] companyNames,String[] provinces )
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
		
		String record= " all details verified valid "+" "+ dateLoss.toString() +" "+ dateClaim.toString()+" "+getIDs(); 
		
		return record +","+peopleNames[indexPeople]+","+gender[indexGender]+","+loss[indexLoss]+","+streets[indexStreets]+ ","+provinces[provinceIndex]+
				","+cities[cityIndex]+","+places[placeIndex]+","+getPostalCode()+","+provinces[provinceIndex1]+","+cities[cityIndex1]
					+","+places[placeIndex1]+","+getPostalCode()+","+ getMaritalStatus(marital)+","+dateBirth.toString()+","
					+"R"+sumInsured+","+"R"+sumRevenue+","+"R"+amountPaid+","+dateStart.toString()+","+dateEnd.toString()+","+peopleNames[otherName]+","+companyNames[indexCompay]+" "+cities[cityIndex]+","+claimIndicator;
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
	
	/*
	 * random number generator between 2 numbers
	 * @ param start ,minimum int
	 * @ param int maximum int
	 */
	public static int randBetween(int start, int end)
	{
		return start +(int)Math.round(Math.random()*(end-start));
	}
	
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		//DataSource dataSource= null;
		CSVLoader loader = new CSVLoader();
		
			

		
		//Instances thisInstance = null;
		try
		
		{
			loader.setSource(new File("/home/hltuser/insuranceRecords.csv"));

			String[] option1s = new String[1]; 
			option1s[0] = "-H";
			loader.setOptions(option1s);
			//dataSource= new DataSource("/home/hltuser/insuranceRecords.csv");
			//thisInstance = dataSource.getDataSet();
			Instances thisInstance = loader.getDataSet();
			thisInstance.setClassIndex(thisInstance.numAttributes()-1);
			SMO mySOM = new SMO();
			 String[] options = new String[2];
			 options[0] = "-R";
			 options[1] = "1";
			 StringBuffer sb = new StringBuffer();
			Evaluation eval = new Evaluation(thisInstance);
			//eval.crossValidateModel(classifier, data, numFolds, random, forPredictionsPrinting);
			eval.crossValidateModel(mySOM,thisInstance,10,new Random(1));
			System.out.println(eval.toSummaryString());
			//mySOM.buildClassifier(thisInstance);
		}
		
		///predictions = java.lange.StringBuffer.new
			//	eval.crossValidateModel(j48, newData, 10, Random.new(1), predictions, Range.new('1'), true)
		
		catch(Exception e)
		{
			
			System.out.println(e.getMessage());
		}
		
		//generateData("/home/hltuser/insuranceRecords.csv");
		
	}

}
