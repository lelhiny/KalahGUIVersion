package kalah;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

import util.Display;
import util.Player;
import com.qualitascorpus.testsupport.IO;

public class ButtonSample extends JFrame implements ActionListener {
   public GUIDisplay GUI; 
  // ArrayList<Player> players;
    public ButtonSample() {
    	GUI = new GUIDisplay();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300, 200);
        setLocation(100, 100);
        setLayout(new BorderLayout());
        JPanel panel = new JPanel();
       // this.players = players;
        
       // BorderLayout layout = new GroupLayout(panel);
       // layout.setAutoCreateGaps(true);
        //layout.setAutoCreateContainerGaps(true);
        
        
        
      //  panel.setPreferredSize(new Dimension(500,500));
     //   panel.setLocation(100,100);
       // panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        JLabel welcomeLabel = new JLabel("Welcome To Kalah!!!!!!!!!!", JLabel.CENTER);
        add(welcomeLabel, BorderLayout.CENTER);
       // add(welcomeLabel);
         // welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        //welcomeLabel.setVerticalAlignment(SwingConstants.CENTER);
       // welcomeLabel.setSize(200, 200);
       // add(welcomeLabel);
      // panel.add(welcomeLabel);
         
    //   JButton VDisplayBtn = new JButton("Vertical Display");
      //  VDisplayBtn.addActionListener(this);
      //  VDisplay.setPreferredSize(new Dimension(500, 100));
     //   VDisplay.setBounds(MAXIMIZED_HORIZ, NORMAL, MAXIMIZED_HORIZ, NORMAL);
     //   JButton robotModeBtn = new JButton("Robot Mode");
       // robotModeBtn.addActionListener(this);
        
        JButton normalBtn = new JButton("Play");
        normalBtn.addActionListener(this);
        add(normalBtn, BorderLayout.SOUTH);
        
      //  panel.add(VDisplay);
        //panel.add(RobotMode);
      // panel.setBounds(this.getWidth(), NORMAL, this.getWidth(), NORMAL);
      /*  panel.setPreferredSize(new Dimension(this.getWidth(), this.getHeight()));
       panel.setAlignmentX(0);
        add(panel);
        panel.setAlignmentX(CENTER_ALIGNMENT);
        
        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addComponent(welcomeLabel)
                .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(
                GroupLayout.Alignment.LEADING)
              .addComponent(VDisplayBtn)
                .addComponent(robotModeBtn)
                .addComponent(normalBtn));*/
          
           /*  layout.setVerticalGroup(layout.createSequentialGroup()
                .addComponent(welcomeLabel)
                .addComponent(VDisplayBtn)
             .addComponent(robotModeBtn)
              .addComponent(normalBtn));
             
        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(
                 GroupLayout.Alignment.CENTER)
                .addComponent(welcomeLabel))));
        
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addComponent(welcomeLabel)
                .addComponent(normalBtn));
             
             panel.setLayout(layout);        
        add(panel);
            */
        setVisible(true);
    }

  //  public static void main(String[] args) {
     //   new ButtonSample();
    //}

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

   /*     if (command.equals("Vertical Display")) {
            VDisplayMethod();
        }
        if (command.equals("Robot Mode")) {
            RobotModeMethod();
        }*/
        if (command.equals("Play")) {
            NormalMethod();
        }
    }

    public void VDisplayMethod() {
        JOptionPane.showMessageDialog(this, "Hello, World 1 !!!!!");
    }
    public void RobotModeMethod() {
        JOptionPane.showMessageDialog(this, "Hello, World 2 !!!!!");
    }
    public void NormalMethod() {
       // JOptionPane.showMessageDialog(this, "Hello, World 3 !!!!!");
    	setVisible(false);
    //	GUI.display(players);
    }
    public Display getDisplay() {
    	return GUI;
    }
}