package kalah;
import java.util.ArrayList;
import java.util.regex.Pattern;

/*import com.qualitascorpus.testsupport.IO;
import com.qualitascorpus.testsupport.MockIO;

import kalah.Kalah;
import util.Display;
import util.House;
import util.Player;

public class GUIDisplay {
	private Player playerOne;
	private Player playerTwo;
	private ArrayList<House> P1Houses;
	private ArrayList<House> P2Houses;
	public GUIDisplay(IO io) {
		new Kalah().play(new MockIO(), false, true);
	}
	public void displayKalah(ArrayList<Player> players) {
		initializeParameters(players);
		printKalah();
		
	}

}*/

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.*;
import javax.swing.border.EmptyBorder;



import util.Constants;
import util.Display;
import util.House;
import util.Message;
import util.Player;

public class GUIDisplay  extends Display implements ActionListener{
   private JFrame mainFrame;
   private JLabel headerLabel;
   private JLabel footerLabel;
   private JPanel controlPanel;
   FileWriter f; 
   BufferedWriter out;
   GUIDisplay swingLayoutDemo;
   public static String GUIMessage = "";
   public static String winnerMessage = "";
   public GUIDisplay(){
	   prepareGUI();
   }
   public void displayKalah(ArrayList<Player> players) {
	   if( swingLayoutDemo == null) {
	   swingLayoutDemo = new GUIDisplay();  
	   }
	 
	   swingLayoutDemo.showGridBagLayoutDemo(players);     
	   checkIfWinnerMessage();
   }
   private void prepareGUI(){
	
		try {
			f = new FileWriter("input");
			
	            out = new BufferedWriter(f);
	            
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.print("DID NOT FIND FILLLLLE");
		}
	
      mainFrame = new JFrame("Kalah Board");
      mainFrame.setSize(700,400);
      mainFrame.setLayout(new GridLayout(4, 1));
      headerLabel = new JLabel("",JLabel.CENTER );
      footerLabel = new JLabel("",JLabel.CENTER );
 
      mainFrame.addWindowListener(new WindowAdapter() {
         public void windowClosing(WindowEvent windowEvent){
            System.exit(0);
         }        
      });    
  
     mainFrame.add(headerLabel);
      mainFrame.add(footerLabel);
    
   }
   private void showGridBagLayoutDemo(ArrayList<Player> players){
	   removePanel();
      footerLabel.setText(Message.GUI_MSG_1); 
      headerLabel.setText("<HTML>" + GUIMessage + "</HTML>");
      controlPanel = new JPanel();
      Player player1 = players.get(Constants.P1_INDEX);
      Player player2 = players.get(Constants.P2_INDEX);
      ArrayList<House> P1houses = player1.getHouses();
      ArrayList<House> P2houses = player2.getHouses();
 
      GridBagLayout layout = new GridBagLayout();

     
      controlPanel.setLayout(layout);
      GridBagConstraints gbc = new GridBagConstraints();
      JButton btn1 = new JButton("6["+  _decimalFormat.formatDigits(P2houses.get(5).getSeed()) +"]");
      btn1.setBackground(Color.RED);
      gbc.fill = GridBagConstraints.HORIZONTAL;
      gbc.gridx = 0;
      gbc.gridy = 0;
      JLabel P2 = new JLabel(" P2 ");
      P2.setBackground(Color.red);
      P2.setAlignmentX(SwingConstants.CENTER);
      P2.setAlignmentY(SwingConstants.CENTER);
      P2.setOpaque(true);
      P2.setBorder(new EmptyBorder(5,10,5,10));//top,left,bottom,right
      controlPanel.add(P2, gbc);
      
      gbc.gridx = 1;
      gbc.gridy = 0;
  
      btn1.setEnabled(false);
      controlPanel.add(btn1 ,gbc);

      gbc.gridx = 2;
      gbc.gridy = 0;
      
      JButton btn2 = new JButton("5["+  _decimalFormat.formatDigits(P2houses.get(4).getSeed()) +"]");
      btn2.setBackground(Color.RED);
     
      btn2.setEnabled(false);
      controlPanel.add(btn2 ,gbc);
      
      gbc.gridx = 3;
      gbc.gridy = 0;
      
      JButton btn3 = new JButton("4["+ _decimalFormat.formatDigits(P2houses.get(3).getSeed()) +"]");
      btn3.setBackground(Color.RED);
 
      btn3.setEnabled(false);
      controlPanel.add(btn3 ,gbc);
      
      gbc.gridx = 4;
      gbc.gridy = 0;
      
      JButton btn4 = new JButton("3["+ _decimalFormat.formatDigits(P2houses.get(2).getSeed()) +"]");
      btn4.setBackground(Color.RED);
    
      btn4.setEnabled(false);
      controlPanel.add(btn4 ,gbc);
      
      gbc.gridx = 5;
      gbc.gridy = 0;
      
      JButton btn5 = new JButton("2["+ _decimalFormat.formatDigits(P2houses.get(1).getSeed()) +"]");
      btn5.setBackground(Color.RED);
    
      btn5.setEnabled(false);
      controlPanel.add(btn5 ,gbc);
      
      gbc.gridx = 6;
      gbc.gridy = 0;
      
      JButton btn6 = new JButton("1["+ _decimalFormat.formatDigits(P2houses.get(0).getSeed()) +"]");
      btn6.setBackground(Color.RED);
   
      btn6.setEnabled(false);
      controlPanel.add(btn6 ,gbc);
      
      gbc.gridx = 7;
      gbc.gridy = 0;
      
      JLabel P1Score = new JLabel(_decimalFormat.formatDigits(player1.getScore()));
      P1Score.setBackground(Color.BLUE);
      P1Score.setOpaque(true);
      P1Score.setBorder(new EmptyBorder(5,10,5,10));//top,left,bottom,right
      controlPanel.add(P1Score ,gbc);
     

      gbc.fill = GridBagConstraints.HORIZONTAL;
   
      gbc.gridx = 0;
      gbc.gridy = 1;
      
      
      JLabel P2Score = new JLabel(_decimalFormat.formatDigits(player2.getScore()));
      P2Score.setBackground(Color.red);
      P2Score.setAlignmentX(SwingConstants.CENTER);
      P2Score.setAlignmentY(SwingConstants.CENTER);
      P2Score.setOpaque(true);
      P2Score.setBorder(new EmptyBorder(5,10,5,10));//top,left,bottom,right
      controlPanel.add(P2Score, gbc);
      gbc.gridx = 1;
      gbc.gridy = 1;
      
      JButton btn1_P2 = new JButton("1["+ _decimalFormat.formatDigits(P1houses.get(0).getSeed()) +"]");
      btn1_P2.setBackground(Color.BLUE);
      btn1_P2.addActionListener(this);
      if( P1houses.get(0).getSeed() == 0) {
    	  btn1_P2.setEnabled(false);
      }
      controlPanel.add(btn1_P2 ,gbc);
      

      gbc.gridx = 2;
      gbc.gridy = 1; 
      
      JButton btn2_P2 = new JButton("2["+ _decimalFormat.formatDigits(P1houses.get(1).getSeed()) +"]");
      btn2_P2.setBackground(Color.BLUE);
      btn2_P2.addActionListener(this);
      if( P1houses.get(1).getSeed() == 0) {
    	  btn2_P2.setEnabled(false);
      }
      controlPanel.add(btn2_P2 ,gbc);
      
      gbc.gridx = 3;
      gbc.gridy = 1; 
      
      JButton btn3_P2 = new JButton("3["+ _decimalFormat.formatDigits(P1houses.get(2).getSeed()) +"]");
      btn3_P2.setBackground(Color.BLUE);
      btn3_P2.addActionListener(this);
      if( P1houses.get(2).getSeed() == 0) {
    	  btn3_P2.setEnabled(false);
      }
      controlPanel.add(btn3_P2 ,gbc);
      
      gbc.gridx = 4;
      gbc.gridy = 1; 
      
      JButton btn4_P2 = new JButton("4["+ _decimalFormat.formatDigits(P1houses.get(3).getSeed()) +"]");
      btn4_P2.setBackground(Color.BLUE);
      btn4_P2.addActionListener(this);
      if( P1houses.get(3).getSeed() == 0) {
    	  btn4_P2.setEnabled(false);
      }
      controlPanel.add(btn4_P2 ,gbc);
      
      gbc.gridx = 5;
      gbc.gridy = 1; 
      
      JButton btn5_P2 = new JButton("5["+ _decimalFormat.formatDigits(P1houses.get(4).getSeed()) +"]");
      btn5_P2.setBackground(Color.BLUE);
      btn5_P2.addActionListener(this);
      if( P1houses.get(4).getSeed() == 0) {
    	  btn5_P2.setEnabled(false);
      }
      controlPanel.add(btn5_P2 ,gbc);
      
      gbc.gridx = 6;
      gbc.gridy = 1; 
      
      JButton btn6_P2 = new JButton("6["+ _decimalFormat.formatDigits(P1houses.get(5).getSeed()) +"]");
      btn6_P2.setBackground(Color.BLUE);
      btn6_P2.addActionListener(this);
      if( P1houses.get(5).getSeed() == 0) {
    	  btn6_P2.setEnabled(false);
      }
      controlPanel.add(btn6_P2 ,gbc);
      
      gbc.gridwidth = GridBagConstraints.REMAINDER; 
      
      gbc.gridx = 7;
      gbc.gridy = 1; 
      
      JLabel P1 = new JLabel(" P1 ");
      P1.setBackground(Color.BLUE);
      P1.setAlignmentX(SwingConstants.CENTER);
      P1.setAlignmentY(SwingConstants.CENTER);
      P1.setOpaque(true);
      P1.setBorder(new EmptyBorder(5,10,5,10));//top,left,bottom,right
      controlPanel.add(P1, gbc);
      gbc.gridx = 0;
      gbc.gridy = 2; 
      gbc.weightx = 0;
      JButton QuitBtn = new JButton("Quit");
      controlPanel.add(QuitBtn, gbc);
      QuitBtn.addActionListener(this); 
      mainFrame.add(controlPanel);
      mainFrame.setVisible(true);  
   }
@Override
public void actionPerformed(ActionEvent e) {
	 String command = e.getActionCommand();
	
	if ( Pattern.compile("6....").matcher(command).matches()) {
		try {
			clearGUIMessage();
			clearFile();
			out.write(6);
			out.flush();
			//System.out.println("Button Pressed!!!!!!!!!!!");
			removePanel();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.out.println("Did n't find file");
		}
        
    }
	if (Pattern.compile("5....").matcher(command).matches()) {
		try {
			 clearGUIMessage();
			clearFile();
			out.write(5);
			out.flush();
			//System.out.println("Button Pressed!!!!!!!!!!!");
			removePanel();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.out.println("Did n't find file");
		}
        
    }
	if (Pattern.compile("4....").matcher(command).matches()) {
		try {
			 clearGUIMessage();
			clearFile();
			out.write(4);
			out.flush();
			//System.out.println("Button Pressed!!!!!!!!!!!");
			removePanel();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.out.println("Did n't find file");
		}
        
    }
	if (Pattern.compile("3....").matcher(command).matches()) {
		try {
			 clearGUIMessage();
			clearFile();
			out.write(3);
			out.flush();
		//	System.out.println("Button Pressed!!!!!!!!!!!");
			removePanel(); 
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.out.println("Did n't find file");
		}
        
    }
	if (Pattern.compile("2....").matcher(command).matches()) {
		try {
			 clearGUIMessage();
			clearFile();
			out.write(2);
			out.flush();
		//	System.out.println("Button Pressed!!!!!!!!!!!");
			removePanel(); 
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.out.println("Did n't find file");
		}
        
    }
	if (Pattern.compile("1....").matcher(command).matches()) {
		try {
			 clearGUIMessage();
			clearFile();
			out.write(1);
			out.flush();
		//	System.out.println("Button Pressed!!!!!!!!!!!");
			removePanel(); 
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.out.println("Did n't find file");
		}
        
    }
	
	if(command.equals("Quit")) {
		 JDialog d = new JDialog(mainFrame, "Kalah");
		 JPanel p = new JPanel(new BorderLayout());
		
         // create a label
         JLabel l = new JLabel("Game Over!!!!", SwingConstants.CENTER);
        
         p.add(l, BorderLayout.CENTER);
         JButton OkBtn = new JButton("Ok");
         OkBtn.addActionListener(this);
         p.add(OkBtn, BorderLayout.SOUTH);
         d.add(p);
         // setsize of dialog
         d.setSize(300, 300);
         d.setLocationRelativeTo(mainFrame);
        mainFrame.enable(false);
         // set visibility of dialog
         d.setVisible(true);
        
	}
	if(command.equals("Ok")) {
		mainFrame.dispatchEvent(new WindowEvent(mainFrame, WindowEvent.WINDOW_CLOSING));
	}
}
private void clearFile() {
	PrintWriter writer = null;
	try {
		writer = new PrintWriter("input");
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		
	}
	writer.print("");
	writer.close();
}
private void removePanel() {
	if(controlPanel != null && mainFrame != null) {
	//System.out.print("Remove Panel");
	 mainFrame.getContentPane().remove(controlPanel);
	  mainFrame.validate();
	  mainFrame.repaint();
	}
}
private void clearGUIMessage() {
	GUIMessage = "";
}

private void checkIfWinnerMessage() {
	//System.out.println("Inside check if winner");
	if(!winnerMessage.isEmpty()) {
		 JDialog d = new JDialog(mainFrame, "Kalah");
		 JPanel p = new JPanel(new BorderLayout());
		
         // create a label
         JLabel l = new JLabel("<HTML>" + winnerMessage + "</HTML>", SwingConstants.CENTER);
        
         p.add(l, BorderLayout.CENTER);
         JButton OkBtn = new JButton("Ok");
         OkBtn.addActionListener(this);
         p.add(OkBtn, BorderLayout.SOUTH);
         d.add(p);
         // setsize of dialog
         d.setSize(300, 300);
         d.setLocationRelativeTo(mainFrame);
        mainFrame.enable(false);
         // set visibility of dialog
         d.setVisible(true);
		
	} 
	
}
}

	