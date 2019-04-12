
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class ElevatorTester extends JPanel implements ActionListener {

  private JButton[] buttons;
  private JButton act;
  private Elevator theElevator;
  
  private JButton save;
  private JButton load;
  
  public ElevatorTester() {
  	super(new BorderLayout());
  	
  	buttons = new JButton[3];
  	buttons[0] = new JButton("1");
  	buttons[1] = new JButton("2");
  	buttons[2] = new JButton("3");
  	act = new JButton("ACT");
  	save = new JButton("SAVE");
  	load = new JButton("LOAD");
  	
  	for(JButton b : buttons)
  		b.setBackground(Color.LIGHT_GRAY);
  	
  	theElevator = new Elevator(act,buttons);
  	add(theElevator, BorderLayout.CENTER);
  	
  	JPanel right = new JPanel(new GridLayout(1,2));
  	JPanel top = new JPanel();
  	JPanel bottom = new JPanel();
  	
  	JPanel buttonBox = new JPanel(new GridLayout(3,1));
  	for (int i = 2; i >= 0; i--) {
  		JButton b = buttons[i];
  		b.addActionListener(this);
  		buttonBox.add(b);
  	}
  	top.add(buttonBox);
  	right.add(top);
  	
  	load.addActionListener(this);
  	right.add(load);
  	
  	save.addActionListener(this);
  	right.add(save);
  	
  	act.addActionListener(this);
  	bottom.add(act);
  	right.add(act);
  	
  	add(right, BorderLayout.NORTH);
  }
  
  
  public void actionPerformed(ActionEvent e) {
  	for (int i = 0; i < 3; i++) {
  		if (e.getSource() == buttons[i]) {
  			theElevator.pushButton(i);
  			return;
  		}
  	}
  	if (e.getSource() == act) {
  		theElevator.act();
  	}
  	
  	if (e.getSource() == save) {
  		
  		// call method to save the elevator
  		Elevator.saveState(theElevator, "elevator.sse");
  		
  	}
  	
  	if (e.getSource() == load) {
  		
  		// call the method to load the elevator
  		// if its null, do nothing
  		// if its not null, initialize the transient fields
  			// initialize the transient fields
  			// overwrite the old elevator
  		Elevator el = Elevator.loadState("elevator.sse");
  		if (null != el) {
  			el.initializeGUI(act, buttons);
  			theElevator = el;
  			revalidate();
  		}
  		
  	}
  		
  }
  
	
  public static void main(String[] args)
  {
    JFrame w = new JFrame("Elevator Simulation");
    w.setBounds(100, 100, 480, 640);
    w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    ElevatorTester panel = new ElevatorTester();
    w.add(panel);
    w.setResizable(true);
    w.setVisible(true);
    
  }
	
}
