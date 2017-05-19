package com.security.FraudData;
import weka.core.Instances;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class DriverClass extends JPanel
{
	public static  TextArea displayText;
	boolean datagenerated = false;
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
		 c.anchor = c.BASELINE_LEADING;
		 c.insets.top = 40;
		 c.insets.bottom = 40;
		 c.insets.left = 40;
		 c.insets.right = 40;
		 
		 c.gridx = 0;
		 c.gridy = 0;
		 c.gridheight = 1;
		 c.gridwidth = 2;
		 c.ipadx = 35;
		 makebutton("Generate", gridBag, c);
		 
		 c.gridx = 3;
		 c.gridy = 0;
		 c.gridheight = 10;
		 c.gridwidth = 20;
		 c.ipadx = 0;
		 makeTextArea("            ", gridBag, c);
		 
		 
		 c.gridx = 0;
		 c.gridy = 1;
		 c.gridheight = 1;
		 c.gridwidth = 1;
		 c.ipadx = 0;
		 makebutton("Annonymize", gridBag, c);
		 
		 c.gridx = 0;
		 c.gridy = 2;
		 c.gridheight = 1;
		 c.gridwidth = 2;
		 c.ipadx = 0;
		 makebutton("View data", gridBag, c);
		 
		 c.gridx = 0;
		 c.gridy = 3;
		 c.gridheight = 1;
		 c.gridwidth = 1;
		 c.ipadx = 0;
		 makeTextField("View activities", gridBag, c);
		 
		 c.gridx = 0;
		 c.gridy = 4;
		 c.gridheight = 1;
		 c.gridwidth = 2;
		 c.ipadx = 0;
		 makebutton("TrainClassifier", gridBag, c);
		 
		 c.gridx = 0;
		 c.gridy = 5;
		 c.gridheight = 1;
		 c.gridwidth = 2;
		 c.ipadx = 0;
		 makebutton("Evaluate", gridBag, c);
		 //frame.setPreferredSize(new Dimension(600, 700));
		// frame.setSize(900,1000);
		 //frame.setSize(1000, 1200);
		 frame.pack();
		 //frame.setResizable(true);
		 frame.setVisible(true);
		 
		 
	}
	
	protected void makebutton(String name, GridBagLayout gridbag, GridBagConstraints c) {
        JButton button = new JButton(name);
        button.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e){JButton clickedButton =(JButton)e.getSource();
		String label = clickedButton.getText();
		if(label.equalsIgnoreCase("Generate"))
			{
			datagenerated= true;
			GenerateData.generateData("/home/hltuser/rawData.csv");
			//displayText;
			displayText.setText("Done Generating Data.\nLocation /home/hltuser/rawData.csv\n");
			
			}
		if(label.equalsIgnoreCase("Annonymize"))
			{	
				if(datagenerated)
				{
					displayText.setText("Processing data ...please wait\nThis step involves\n removing peoples indentitiesWhich are\nNames\nAnd\nQuasi Indentifiers are generalized sensitive information");
					originalInstances =CreateInstances.getInstances("/home/hltuser/rawData.csv");
					annonymizedInstances =CreateInstances.generalize(originalInstances);
				
		
					CreateInstances.saveInCsv(annonymizedInstances);
					displayText.setText("Generalized data saved in location /home/hltuser/annonymisedRecords.csv\n select view data");
					
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
				displayText.setText("This displays a sample 5 data before Generalization\n and after Generalization was applied\n");
				///CreateInstances.viewInstances(originalInstances,5);
				for(int i=0;i<5;i++)
				{
					displayText.append(originalInstances.instance(i).toString()+"\n");
				}
				displayText.append("=============After generalization============= \n \n");
				for (int i=0;i<5;i++)
				{
					displayText.append(annonymizedInstances.instance(i).toString()+"\n");
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
	 /*public void actionPerformed(ActionEvent e)
	 {
		JButton clickedButton =(JButton)e.getSource();
		String label = clickedButton.getText();
		if(label.equalsIgnoreCase("Generate"))
		{
			
			GenerateData.generateData("~/rawData.csv");
			TextArea tx = DriverClass.displayText;
			tx.setText("Done Generating Data.\nLocation /home/hltuser/rawData.csv\n");
					
			
			
		}
		
     }**/

}
