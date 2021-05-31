package com.lcourtn5.module5;

//Uses print stream re-direction from https://www.codejava.net/java-se/swing/redirect-standard-output-streams-to-jtextarea

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.*;
import javax.swing.text.*;
import com.lcourtn5.module5.Rates.HIKE;
import java.io.*;
import java.util.Calendar;
import java.util.Date;

public class Main extends JPanel implements ActionListener{
	private static final long serialVersionUID = 1L;
	
	//Create and set up the date input
	DateFormat format = new SimpleDateFormat("yyyyMMdd");
	private JFormattedTextField dateTextField = new JFormattedTextField(format);
	//set up variables
	public String hike;
	public int duration;
	public Date date;
	private JTextArea textArea = new JTextArea(20,10);
	//buttons
	private JRadioButton two = new JRadioButton("2 days");
	private JRadioButton three1 = new JRadioButton("3 days");
	private JRadioButton three2 = new JRadioButton("3 days");
	private JRadioButton four = new JRadioButton("4 days");
	private JRadioButton five1 = new JRadioButton("5 days");
	private JRadioButton five2 = new JRadioButton("5 days");
	private JRadioButton seven = new JRadioButton("7 days");
	private JButton submit = new JButton("Submit");
	private JButton clear = new JButton("Clear");
	private ButtonGroup bg = new ButtonGroup();
	//labels
	private JLabel jl1 = new JLabel("Choose a hike and duration: ");
	private JLabel jl2 = new JLabel("Choose a start date (YYYYMMDD): ");
	private JLabel gd = new JLabel("Gardiner Lake");
	private JLabel hr = new JLabel("Hellroaring Plateau");
	private JLabel bp = new JLabel("The Beaten Path");
	private GridBagConstraints gbc = new GridBagConstraints();
	
	public Main() {
	
    	//Create and set up the radio buttons
		three1.setActionCommand("GARDINER 3");
		five1.setActionCommand("GARDINER 5");
		two.setActionCommand("HELLROARING 2");
		three2.setActionCommand("HELLROARING 3");
		four.setActionCommand("HELLROARING 4");
		five2.setActionCommand("BEATEN 5");
		seven.setActionCommand("BEATEN 7");
		//Make and set up the submit button
		submit.setActionCommand("submit");
		clear.setActionCommand("clear");
		
		//Add all the buttons to the group
		bg.add(two);
		bg.add(three1);
		bg.add(three2);
		bg.add(four);
		bg.add(five1);
		bg.add(five2);
		bg.add(seven);
		
		//Add listeners for each button
		two.addActionListener(this);
		three1.addActionListener(this);
		three2.addActionListener(this);
		four.addActionListener(this);
		five1.addActionListener(this);
		five2.addActionListener(this);
		seven.addActionListener(this);
		submit.addActionListener(this);
		clear.addActionListener(this);
		
		//Re-direct stdout to the textArea
		textArea.setEditable(false);
		PrintStream printStream = new PrintStream(new CustomOutputStream(textArea));
		System.setOut(printStream);
		System.setErr(printStream);
		
		//Set up the GridBag
		this.setLayout(new GridBagLayout());
		this.setBorder(BorderFactory.createEtchedBorder());
		
		gbc.fill=GridBagConstraints.HORIZONTAL;
		gbc.gridx=0;
        gbc.gridy = 0;
        gbc.weightx = 0.0;
        add(jl1, gbc);
        
        gbc.weightx = 1.0;
        gbc.gridx = 0;        
        gbc.gridy = 1;
        add(gd, gbc);
        gbc.gridx = 1;    
        add(three1, gbc);
        gbc.gridx = 2;    
        add(five1, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(hr, gbc);
        gbc.gridx = 1;    
        add(two, gbc);
        gbc.gridx = 2;
        add(three2, gbc);
        gbc.gridx = 3;
        add(four, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(bp, gbc);
        gbc.gridx = 1;    
        add(five2, gbc);
        gbc.gridx = 2;    
        add(seven, gbc);
        
        gbc.weightx = 1.0;
        gbc.gridx = 0;        
        gbc.gridy = 4;
        add(jl2, gbc);
        gbc.gridx = 1;  
        add(dateTextField, gbc);
        
        gbc.weightx = 1.0;
        gbc.gridx = 0;        
        gbc.gridy = 5;
        add(submit, gbc);
        gbc.weightx = 1.0;
        gbc.gridx = 1;
        add(clear,gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = .5;
        gbc.weighty = 1;
        add(new JScrollPane(textArea), gbc);
       
    }
	
	public void actionPerformed(ActionEvent e) {
		//Actions performed on buttons section
		String choice = e.getActionCommand();
		
		if (choice == "submit") {
			//Gather the date value and test for valid input
			date = (Date) dateTextField.getValue();
			System.out.println("Hike: " + hike);
			System.out.println("Duration: " + duration);
			if (hike == null) {
				JOptionPane.showMessageDialog(null, "You did not choose a hike and duration.", "Error", JOptionPane.ERROR_MESSAGE);
			} else if (date == null) {
				JOptionPane.showMessageDialog(null, "You did not enter a valid date - format YYYYMMDD.", "Error", JOptionPane.ERROR_MESSAGE);
			} else {
				//Set up the dates
				//get the end date - we don't have to check duration because they are hard-coded
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Calendar c = Calendar.getInstance();
				c.setTime(date);
				c.add(Calendar.DATE, duration - 1);
				String stringEndDate = sdf.format(c.getTime());
				String stringStartDate = sdf.format(date);
				try {
					//unfortunately to get this to work with BookingDay I have to send the date back to string
					int startYear = Integer.parseInt(stringStartDate.split("-")[0]);
					int startMonth = Integer.parseInt(stringStartDate.split("-")[1]);
					int startDay = Integer.parseInt(stringStartDate.split("-")[2]);
					BookingDay startDate = new BookingDay(startYear, startMonth, startDay);
					int endYear = Integer.parseInt(stringEndDate.split("-")[0]);
					int endMonth = Integer.parseInt(stringEndDate.split("-")[1]);
					int endDay = Integer.parseInt(stringEndDate.split("-")[2]);
					BookingDay endDate = new BookingDay(endYear, endMonth, endDay);
					System.out.println("Starting Date: " + startDate);
					System.out.println("Ending Date: " + endDate);
					
					if (!startDate.isValidDate()) {
						JOptionPane.showMessageDialog(null, "This is not a valid day to start a hike.", "Invalid Date", JOptionPane.ERROR_MESSAGE);
					} else if (!endDate.isValidDate()) {
						JOptionPane.showMessageDialog(null, "This is not a valid hike duration with this start date.", "Invalid Date", JOptionPane.ERROR_MESSAGE);
					} else {
						//Find the rates
						Rates rates;
						if (hike.equals("BEATEN")) {
							rates = new Rates(HIKE.BEATEN);
						} else if (hike.equals("HELLROARING")) {
							rates = new Rates(HIKE.HELLROARING);
						} else {
							rates = new Rates(HIKE.GARDINER);
						}
						rates.setBeginDate(startDate);
				        rates.setDuration(duration);
				        System.out.println("Are the dates valid? " + rates.isValidDates());
				        if (rates.isValidDates()) {
				            System.out.println("Total Cost of Trip: $" + rates.getCost());
				            System.out.println("# of Weekdays: " + rates.getNormalDays());
				            System.out.println("# of Weekends: " + rates.getPremiumDays());        
				        } else {
				            System.out.println("Sorry, but " + rates.getDetails());
				        }
					}	
				} catch (NumberFormatException nfe) {
					//this shouldn't hit because the dateTextField defaults to a date it can parse
					JOptionPane.showMessageDialog(null, "There was an error parsing the date.", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		} else if (choice == "clear") {
			//Clear the textarea and the buttons
			try {
				textArea.getDocument().remove(0, textArea.getDocument().getLength());
             } catch (BadLocationException ex) {
                 ex.printStackTrace();
             }
			dateTextField.setValue(null);
			bg.clearSelection();
			
		} else {
			//One of the radio buttons was clicked, setting up values based on radio button selection
			hike = choice.split(" ")[0];
			duration = Integer.parseInt(choice.split(" ")[1]);
		}
	}

    public static void main(String[] args) {
		final JFrame f = new JFrame("Beartooth Hike Selector Tool");
		f.add(new Main());
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Runnable showFrame = new Runnable() {
			public void run() {
			    f.pack();
			    f.setVisible(true);
			}
		};
		SwingUtilities.invokeLater(showFrame);
    }
    
    protected String[] listData = {
	"Gardiner Lake",
	"Hellroaring Plateau",
	"The Beaten Path",
    };
    
}

		
		

