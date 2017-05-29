package com.security.FraudData;
import weka.classifiers.Classifier;
import weka.core.Instances;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class DriverClass extends JPanel
{
	public static  TextArea displayText;
	public static Classifier newClassifier;
	public boolean trained = false;
	boolean datagenerated = false;
	boolean cleaned = false;
	boolean annony = false;
	
	public static  TextField programStatus;
	public static JFrame frame;
	Instances originalInstances;
	Instances annonymizedInstances;
	
	
	
	public static void main(String[] args)
	{
			DriverClass myDriver = new DriverClass();
			myDriver.initialDisplay1();
	}
	public static void initialDisplay()
	{
		
		frame = new JFrame();
		displayText = new TextArea("Displaying Details");
		programStatus = new TextField("Programm status");
		frame.addWindowListener(new WindowAdapter(){ public void windowClosing(WindowEvent e){System.exit(0);}});
		frame.setContentPane(new DriverClass());
		frame.setSize(1000, 1200);
		frame.setTitle("Data mining Simulation ");
		frame.setLayout(new FlowLayout());
		frame.add(new JLabel("Data Mining Simulation"));
		//frame.add(new JLabel(""));
		//frame.add(new JLabel(""));
		frame.add(getButton("Generate"));
		frame.add(programStatus);
		frame.add(displayText);
		
		frame.getContentPane();
		frame.pack();
		frame.setVisible(true);
	}
	public void initialDisplay1()
	{
		frame = new JFrame("Big Data Analysis Simulation tool");
		 GridBagLayout gridBag = new GridBagLayout();
		 GridBagConstraints c = new GridBagConstraints();
		 frame.setLayout(gridBag);
		 c.fill = c.BOTH;
		 c.anchor = c.CENTER;
		 c.insets.top = 35;
		 c.insets.bottom = 35;
		 c.insets.left = 100;
		 c.insets.right = 100;
		 
		 c.gridx = 0;
		 c.gridy = 0;
		 c.gridheight = 1;
		 c.gridwidth = 2;
		 c.ipadx = 35;
		 makebutton("Generate", gridBag, c);
		 c.gridx = 3;
		 c.gridy = 0;
		 c.gridheight = 34;
		 c.gridwidth = 150;
		 c.ipadx = 200;
		 //c.ipady =34;
		 makeTextArea("    Simulation Output Display  ", gridBag, c);
		 
		 
		 c.gridx = 0;
		 c.gridy = 1;
		 c.gridheight = 1;
		 c.gridwidth = 1;
		 c.ipadx = 0;
		 makebutton("Verify data", gridBag, c);
		 
		 c.gridx = 0;
		 c.gridy = 2;
		 c.gridheight = 1;
		 c.gridwidth = 2;
		 c.ipadx = 0;
		 makebutton("Clean data", gridBag, c);
		 
		 c.gridx = 0;
		 c.gridy = 3;
		 c.gridheight = 1;
		 c.gridwidth = 2;
		 c.ipadx = 0;
		 makebutton("Annonymise", gridBag, c);
		 
		 
		 
		 c.gridx = 0;
		 c.gridy = 4;
		 c.gridheight = 1;
		 c.gridwidth = 1;
		 c.ipadx = 0;
		 makebutton("View Data", gridBag, c);
		 
		 c.gridx = 0;
		 c.gridy = 5;
		 c.gridheight = 1;
		 c.gridwidth = 1;
		 c.ipadx = 0;
		 makebutton("Cluster data", gridBag, c);
		 
		 
		 
		 c.gridx = 0;
		 c.gridy = 6;
		 c.gridheight = 1;
		 c.gridwidth = 2;
		 c.ipadx = 0;
		 makebutton("TrainClassifier", gridBag, c);
		 
		 c.gridx = 0;
		 c.gridy = 7;
		 c.gridheight = 1;
		 c.gridwidth = 2;
		 c.ipadx = 0;
		 makebutton("Evaluate", gridBag, c);
		 frame.pack();

		 frame.setVisible(true);
		 
		 
	}
	
	protected void makebutton(String name, GridBagLayout gridbag, GridBagConstraints c) {
        JButton button = new JButton(name);
        button.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e){JButton clickedButton =(JButton)e.getSource();
		String label = clickedButton.getText();
		if(label.equalsIgnoreCase("Generate"))
			{
			datagenerated= true;
			GenerateData.generateData("/home/hltuser/runSimulation/rawData.csv");
			//displayText;
			displayText.setText("Done Generating Data.\nLocation: /home/hltuser/runSimulation/rawData.csv\n");
			CleanData.displayStats("/home/hltuser/runSimulation/rawData.csv");
			displayText.append("\n Analysis of Data  Generated\n"+CleanData.update);
			
			}
		if(label.equalsIgnoreCase("Annonymise"))
			{	
				if(datagenerated)
				{	
					if(cleaned)
					{
						Instances oin = originalInstances;
						displayText.setText("Processing data ...please wait\nThis step involves\n1. Removing peoples indentities:Names\nAnd\nQuasi Indentifiers are generalized");
						annonymizedInstances =CreateInstances.generalize(oin);
						CreateInstances.saveInCsv(annonymizedInstances);
						displayText.setText("Generalized data saved in location /home/hltuser/runSimulation/annonymisedRecords.csv\n select view data");
						CleanData.displayStats("/home/hltuser/runSimulation/annonymisedRecords.csv");
						displayText.append("\nData analysis after Annonyimizatio\n"+CleanData.update);
						annony = true;
					}
					else
					{
						displayText.setText("Please clean data first to ensure records are valid data: remove incomplete records");
						
					}
					
					
				}
				else
				{
					//.DriverClass. ta= DriverClass.;
					displayText.setText("Cannot generalize, data was not generated please click the Generate button");
				}
				
				
			}
		if(label.equalsIgnoreCase("View data"))
		{
			if(datagenerated)
			{
				if(cleaned && annony)
				{
					displayText.setText("This displays a sample 5 data Before Generalization\n and after Generalization was applied\n");
				///CreateInstances.viewInstances(originalInstances,5);
					for(int i=0;i<5;i++)
					{
						displayText.append(originalInstances.instance(i).toString()+"\n");
						displayText.append("Total Attributes before "+originalInstances.numAttributes());
						
						//displayText.append(CleanData.update);
					}
					displayText.append("\n=============After generalization=============\n \n");
					for (int i=0;i<5;i++)
					{
						displayText.append(annonymizedInstances.instance(i).toString()+"\n");
						displayText.append("Total Attributes After Annonyimization "+annonymizedInstances.numAttributes());
						
					}
				}
				else
				{
					displayText.setText("This displays 5 samples of records before generalization\n");
					for(int i=0;i<5;i++)
					{
						displayText.append(originalInstances.instance(i).toString()+"\n");
					}
					
					displayText.append("Please annonyimise data and view to see the differnces");
					
				}
			}
			else
			{
				displayText.setText("Data was not generated please proceed to click generated button");
			}
		}
		
		if(label.equalsIgnoreCase("Verify Data"))
		{
			if(datagenerated)
			{
				if(cleaned)
				{
					displayText.setText("Calling verfiy method... \n");
					CleanData.verifyCleanInstances("/home/hltuser/runSimulation/cleanrawData.csv");
					displayText.append(CleanData.update);
					CleanData.displayStats("/home/hltuser/runSimulation/cleanrawData.csv");
					displayText.append("\nData analysis after Sanitazing\n"+CleanData.update);
					  //("/home/hltuser/runSimulation/cleanrawData.csv);
					
				}
				else
				{
					displayText.setText("Calling verfiy method... \n");
					
					CleanData.verifyCleanInstances("/home/hltuser/runSimulation/rawData.csv");
					displayText.append(CleanData.update);
					CleanData.displayStats("/home/hltuser/runSimulation/rawData.csv");
					displayText.append("\nData analysis\n"+CleanData.update);
					
					
					
				}
				
			}
			else
			{
				displayText.setText("Data was not generated please proceed to click generated button");
			}
			
		}
		
		if(label.equalsIgnoreCase("Evaluate"))
		{
			if(trained)
			{
				
					displayText.setText("Cross Evaluating classifier started..\nPlease wait ...");
					//Instances train = CreateInstances.getInstances("/home/hltuser/runSimulation/annonymisedRecords.csv");
					EvaluateClassifier.foldsEvaluation(annonymizedInstances,"nb",10);
					String st = EvaluateClassifier.allStats;
					displayText.append("\nDone cross evaluating\nDislaying Statistics\n");
					displayText.append(st);
					
			}
			else
			{
				displayText.setText("Classifier not trained, please ensure you have trained before evaluating classifier");
			}
			
		}
		
		if(label.equalsIgnoreCase("TrainClassifier"))
		{
			if(datagenerated)
			{
				if(cleaned && annony)
				{
					displayText.setText("Training Naive Bayes classifier started..\nPlease wait ...\n");
					//Instances train = CreateInstances.getInstances("/home/hltuser/runSimulation/annonymisedRecords.csv");
					newClassifier=TrainClassifiers.trainClassifer(annonymizedInstances,"nb");
					displayText.append("Done training classifier ... you can evaluate the classifier");
					trained =true;
					//(annonymizedInstances)
				}
				else
				{
					displayText.setText("Data was not cleaned and annoyimsed cannot continue trainnig classifier, please clean and annonymize data \n");
					
				}
				
			}
			else
			{
				displayText.setText("Data was not generated please proceed to click generated button");
			}
			
		}
		
		if(label.equalsIgnoreCase("Clean Data"))
		{
			if(datagenerated)
			{
				if(cleaned)
				{
					displayText.setText("data was cleaned proceed to annonyimize data");
				}
				else
				{
					displayText.setText("Pleas wait .. data is cleaned \n");
					
					CleanData.cleanInstances("/home/hltuser/runSimulation/rawData.csv","/home/hltuser/runSimulation/cleanrawData.csv");
					displayText.append(CleanData.update+" in /home/hltuser/runSimulation/cleanrawData.csv\nPlease view data to see how clean and unclean samples compare\n");
					originalInstances =CreateInstances.getInstances("/home/hltuser/runSimulation/cleanrawData.csv");
					//CleanData.displayStats("/home/hltuser/runSimulation/cleanrawData.csv");
					//displayText.append("Data Analysis after" CleanData.update);
					
					cleaned = true;
				}
				
			}
			else
			{
				displayText.setText("Data was not generated please proceed to click generated button");
			}
			
		}
		
		
		}
        });
        gridbag.setConstraints(button, c);
        frame.add(button);
    }
	
	protected void makeTextField(String name, GridBagLayout gridbag, GridBagConstraints c) {
		programStatus = new TextField(name);
        gridbag.setConstraints(programStatus, c);
        frame.add(programStatus);
    }
	protected void makeTextArea(String name, GridBagLayout gridbag, GridBagConstraints c) {
		displayText = new TextArea(name);
		displayText.setEditable(true);
        gridbag.setConstraints(displayText, c);
        frame.add(displayText);
    }
	
	public static JButton getButton(String label)
	{
		JButton newButton =new JButton(label);
		newButton.setSize(10,10);
		newButton.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e){JButton clickedButton =(JButton)e.getSource();
		String label = clickedButton.getText();
		if(label.equalsIgnoreCase("Generate"))
		{
			
			GenerateData.generateData("/home/hltuser/rawData.csv");
			TextField tf = DriverClass.programStatus;
			tf.setText("Done Generating Data.\nLocation /home/hltuser/rawData.csv\n");
					
			
			
		}}});
		
		return newButton;
		
	}
}
