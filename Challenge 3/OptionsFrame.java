import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

public class OptionsFrame extends JDialog implements ActionListener{
	protected JTextField RField;
	protected CanvasPane canvas;
	protected JTextField rField;
	protected JTextField OField;
	protected JComboBox<String> colourCombo;
	
	public OptionsFrame(CanvasPane canvas) {
		this.canvas = canvas;
		this.setSize(250,180);
		this.setResizable(false);
		this.setTitle("Add new Graph");
		
		
		JLabel RLabel = new JLabel("R Value: ");
		RField = new JTextField(15);
		RLabel.setLabelFor(RField);
		
		JLabel rLabel = new JLabel("r Value: ");
		rField = new JTextField(15);
		rLabel.setLabelFor(rField);
		
		JLabel OLabel = new JLabel("O Value: ");
		OField = new JTextField(15);
		OLabel.setLabelFor(OField);
		
		JLabel colourLabel = new JLabel("Colour: ");
		colourCombo = new JComboBox<String>();
		colourCombo.addItem("Red");
		colourCombo.addItem("Blue");
		colourCombo.addItem("Orange");
		colourCombo.addItem("Magenta");
		colourCombo.addItem("Cyan");
		colourCombo.addItem("Black");
		
		JButton okButton = new JButton("OK");
		okButton.setActionCommand("newGraphOK");
		okButton.addActionListener(this);
		
		//Code below creates relations between different objects so that they are displayed correctly
		SpringLayout layout = new SpringLayout();
		Container pane = this.getContentPane();
		layout.putConstraint(SpringLayout.NORTH, RLabel, 5, SpringLayout.NORTH, pane);
		layout.putConstraint(SpringLayout.NORTH, RField, 5, SpringLayout.NORTH, pane);
		layout.putConstraint(SpringLayout.NORTH, rLabel, 5, SpringLayout.SOUTH, RField);
		layout.putConstraint(SpringLayout.NORTH, rField, 5, SpringLayout.SOUTH, RField);
		layout.putConstraint(SpringLayout.NORTH, OLabel, 5, SpringLayout.SOUTH, rField);
		layout.putConstraint(SpringLayout.NORTH, OField, 5, SpringLayout.SOUTH, rField);
		layout.putConstraint(SpringLayout.NORTH, colourLabel, 5, SpringLayout.SOUTH, OField); //Form of this is the top of the colour label should align with the bottom of the O field
		layout.putConstraint(SpringLayout.NORTH, colourCombo, 5, SpringLayout.SOUTH, OField);
		layout.putConstraint(SpringLayout.NORTH, okButton, 5, SpringLayout.SOUTH, colourCombo);
		
		layout.putConstraint(SpringLayout.WEST, RLabel, 5, SpringLayout.WEST, pane);
		layout.putConstraint(SpringLayout.WEST, rLabel, 5, SpringLayout.WEST, pane);
		layout.putConstraint(SpringLayout.WEST, OLabel, 5, SpringLayout.WEST, pane);
		layout.putConstraint(SpringLayout.WEST, colourLabel, 5, SpringLayout.WEST, pane);
		
		layout.putConstraint(SpringLayout.WEST, RField, 5, SpringLayout.EAST, RLabel);
		layout.putConstraint(SpringLayout.WEST, rField, 5, SpringLayout.EAST, rLabel);
		layout.putConstraint(SpringLayout.WEST, OField, 5, SpringLayout.EAST, OLabel);
		layout.putConstraint(SpringLayout.WEST, colourCombo, 5, SpringLayout.EAST, colourLabel);
		
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, okButton, 5, SpringLayout.HORIZONTAL_CENTER, this.getContentPane());
	
		
		//Adds all components to the frame
		this.add(RLabel);
		this.add(RField);
		this.add(rLabel);
		this.add(rField);
		this.add(OLabel);
		this.add(OField);
		this.add(colourLabel);
		this.add(colourCombo);
		this.add(okButton);
		this.setLayout(layout);
		RField.requestFocusInWindow(); //This allows the text box to have focus when the window is open
		this.setModal(true); //This makes it act like a dialog in that you cannot use the other form while it is open
		this.setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand().equals("newGraphOK")){
			int R = Integer.parseInt(RField.getText());
			int r = Integer.parseInt(rField.getText());
			int O = Integer.parseInt(OField.getText());
			
			Color colour;
			int selectedItem = colourCombo.getSelectedIndex();
			if (selectedItem == 0){
				colour = Color.RED;
			} else if (selectedItem == 1){
				colour = Color.BLUE;
			} else if (selectedItem == 2){
				colour = Color.ORANGE;
			} else if (selectedItem == 3){
				colour = Color.MAGENTA;
			} else if (selectedItem == 4){
				colour = Color.CYAN;
			} else {
				colour = Color.BLACK;
			}
			
			//Adds a new hypercycloid object to the canvas ready to be drawn
			this.canvas.addGraph(new Hypercycloid(R,r,O,colour));
			this.setVisible(false);
		}
	}
}
