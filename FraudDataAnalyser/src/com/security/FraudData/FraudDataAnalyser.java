package com.security.FraudData;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class FraudDataAnalyser {
	
	private static final String ChronoUnits = null;
	/*public static String getField( )
	{
		int rand = randBetween(0,currentField.length-1);
		return currentField[rand];
	}*/
	public static String getIDs()
	{
		return ""+randBetween(10000,50000)+","+randBetween(60000,80000);
	}
	public static void generateData()
	{
		/*indictor,reason,DateOfLoss,DateOfClaim,BrokerID,InsuredID,InsuredName,InsuredSurname,Gender,KindOfLoss
		* PolicyHolderStreet,PolicyHolderProvince,PolicyHolderCity,PolicyHolderArea,PolicyHolderPostalCode,ProvinceLoss,CityLoss,AreaLoss,PostalCode,MaritalStatus,DateBirth
		* SumIsured,TotalPoliciesRevenue,AmountPaid,PolicyStartDate,PolicyEndDate,NameOtherParty,SurnameOtherParty
		* ClaimServiceProvider
		*Alphen Park Arcadia Ashlea Gardens Bailey's Muckleneuk Brooklyn Brummeria Bryntirion
	    Clydesdale Colby Constantia Park Die Wilgers Eastcliff Eastwoow Elardus Park Equestria Erasmuskloof
	    Erasmusrand Faerie Glen Garsfontein Groenkloof Grootfontein Hatfield Hazeldean Hazelwood Hillcrest
	    Kilberry La Montagne Lydiana Lynnrodene Lynnwood Lynnwood Glen Lynnwood Manor
	    Lynnwood Park Lynnwood Ridge Lukasrand Menlo Park Menlyn Meyerspark Monument Park Mooikloof Moreleta Park Muckleneuk
	    Murrayfiel Nieuw Muckleneuk Newlands Olympus Pretorius Park Rietvalleirand Shere A.H.
	    Silver Lakes Sterrewag Sunnyside Trevenna Val de Grace Wapadrand Waterkloof Waterkloof Glen Waterkloof Park
	    Waterkloof Ridge Willow Glen Willow Park Manor Wingate Park Woodhill Woodlands Zwavelpoort
	     
		* Street names : Church, Standza, Vos, khutsong ,prospect, mzilikazi
		* 0002 Pretoria, 
		* 770 Cape Town, places : Mowbray, Rondebosh, Rondebosh East, Salt River, Campus Bay, 
		*Guateng 1550 Springs Gauteng
		*
		*  Dirty records: missing Date of claim,
		*  
		*/
		String claimIndicator ="True";
		String record,reason,brokerId,InsuredId,InsuredName,Surname,Gender,KindOfLoss,insuredStreet,InsuredProvice,InsuredCity,InsuredArrea,InsuredPostCode,eventProvince,eventCity,eventArea,eventPostalCode,insuredMaritalStatus,thirdPartyName,thirdPartySurname,serviceProvider="";
		int sumInsured,sumRevenue,amountPaid =0;
		LocalDate dateLoss=LocalDate.now();
		LocalDate dateClaim =LocalDate.now();
		LocalDate dateBirth =LocalDate.now();
		LocalDate dateStart =LocalDate.now();
		LocalDate dateEnd= LocalDate.now();
		String[] peopleNames= {"John Yarnall","Deirdre Bilski","Deirdre Bilski","Alethea Haven","Jutta Richie","Suellen Mero","Cedric Inouye ","Olevia Racette","Chantel Zirbel  ","Collene Dumais  ","Amiee Bessler","Betsy Lorraine","Dulce Canez  ","","","Amada Almada","Collen Dike","Margareta Obert","Lezlie Krach","Lurlene Slinkard ","Rigoberto Mercado","Yoshiko Avilez  ","Yoshiko Avilez","Ranee Yokley","Aja Tarpley","atum Bamber ","Tawana Metzinger","Jolene Bosworth","Caitlyn Day","Alisa Walling","Creola Waite","Anna Bankhead","Zona Deroche ","Rina Lynch  ","Shondra Sung ","Armanda Wozniak  ","Harris Mcchesney ","Heriberto Langston ","Jaqueline Schunk","Maurita Musselwhite","Gigi Zwart","Harley Farmer","Winona Hewey","Gonzalo Holtzen","Nelda Boateng","Sanford Schubert","Samuel Squier","Ezequiel Montesino","Cordie Manners","January Raborn","Shauna Genovese","Cecile Aichele","Ramiro Mungia","Eli Byerly"};
		String[] places = {"Vaal de Grace","Willow Park","Menlo Park","Meyers Park","Mooikloof","Erasmusrand","Fearie Glen","Lynwood Manor","Lynwood Ridge","Gugulethu","Willow Park","Rondebosch","Reitkol","","Sundra","Hazyview","Malelane","uMlazi","Howick","Osizweni","Madadeni"};
		String[] cities	={"Johanesburg","Pretoria","Kimberly","Durban","Nelspriut","Polokwane","RustenBurg","Bloemfontien"};
		String[] towns  = {"Springs","WitBank","Delmas","Pongola","Kempton Park","Springs","Thabazimbi","Tzaneen","Palaborwa","Middleburg","NewCastle","Thohoyando"};
		String[] streets ={"Prospect","Hill","Hilda","Church","Standza","University","Old Johannesburg Road", "Steve Biko", "Kilimanjaro", "University Rd","Old Pretoria Rd", "Esselen","Lilian Ngoyi","Lynwood Rd","Bel Air"};
		String[] companyNames ={"Volvo car repairs","Super Cars dealer","BMW","Rubex constructors"};
		String[] provinces ={"Gauteng","Limpopo","Mpumalana","Western Cape","Northen Cape","Eastern Cape","KwaZulu Natal","Free State","North West"};
		int day,month,year,yearBirth,yearPolicyStart,yearPolicyEnd,yearDamage,yearClaim =0;
		
		day = randBetween(1,10);
		month=randBetween(1,5);
		yearBirth =randBetween(18,50);
		yearPolicyStart =yearBirth+randBetween(0,9);
		yearPolicyEnd =yearPolicyStart+randBetween(1,9);
		yearClaim = yearPolicyStart +randBetween(1,9);
		sumInsured =randBetween(20000,100000);
		//amountPaid =sumInsured -randBetween(1,19000);
		LocalDate today = LocalDate.now();
		for(int i = 0; i<1000;i++)
		{
			
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
				amountPaid =getValidAmountPaid(sumInsured);
				
			}
			catch(DateTimeException dte)
			{
				System.out.println(dte.getMessage());
			}
			catch(Exception exp)
			{
				System.out.println(exp.getMessage());
			}
			
			record=claimIndicator+" all details verified valid "+getIDs()+" " + dateLoss.toString() +" "+ dateClaim.toString(); 
			
			if(i%10 ==0)
			{
				
				
			}
			
			//change Towns
			if(i%10==0)
			{
					
				
			}
			System.out.println(record);
			day = randBetween(1,10);
			month=randBetween(1,5);
			yearBirth =randBetween(18,50);
			yearPolicyStart =yearBirth+randBetween(0,9);
			yearPolicyEnd =yearPolicyStart+randBetween(1,9);
			yearClaim = yearPolicyStart +randBetween(1,9);
			sumInsured =randBetween(20000,100000);
		}
		
	}
	public static String getAll(String peopleNames[])
	{
		return "";
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
		int changeItem = randBetween(1,3); //help us randomly choose which date field to change
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
		
		generateData();
		
	}

}
