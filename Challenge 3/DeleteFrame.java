import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;

public class DeleteFrame extends JDialog implements ActionListener{
	protected JList listBox;
	protected ArrayList<Hypercycloid> graphs;
	protected CanvasPane canvas;
	public DeleteFrame(CanvasPane canvas){
		this.canvas = canvas;
		this.graphs = canvas.getGraphs();
		this.setSize(250,250);
		this.setTitle("Delete a Graph");
		
		//Creates a list view that adds parameters about each graph
		DefaultListModel<String> listModel = new DefaultListModel();
		for (int i = 0; i < this.graphs.size(); i++){
			Hypercycloid current = this.graphs.get(i);
			listModel.addElement((i+1)+") R:"+current.getR()+", r:"+current.getr()+", O:"+current.getO());
		}
		
		//Creates a new list 
		listBox = new JList<String>(listModel);
		listBox.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		this.add(listBox);
		
		JButton deleteButton = new JButton("Delete");
		deleteButton.setActionCommand("delete");
		deleteButton.addActionListener(this);
		
		this.add(deleteButton);
		this.setModal(true);
		
		if (this.graphs.size() == 0){
			JOptionPane.showMessageDialog(null, "No graphs to delete");
		} else {
			this.setVisible(true);
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()){
		case "delete":
			if (this.listBox.getSelectedValuesList().size() != 0){
				this.canvas.deleteGraph(this.listBox.getSelectedIndex());
			}
			this.setVisible(false);
		}
	}
}
