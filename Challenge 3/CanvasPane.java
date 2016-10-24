import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JPanel;

public class CanvasPane extends JPanel{
	ArrayList<Hypercycloid> graphs;
	public CanvasPane(){
		graphs = new ArrayList<>();
	}
	
	protected void paintComponent(Graphics g){	
		super.paintComponent(g);
		//Draws every hypercycloid that is stored
		for (int i = 0; i<this.graphs.size();i++){
			this.graphs.get(i).paint(g, this);
		}
	}
	
	public void addGraph(Hypercycloid h){
		this.graphs.add(h);
		this.repaint();		
	}
	
	public void deleteGraph(int index){
		this.graphs.remove(index);
		this.repaint();
	}
	
	public void clear(){
		this.graphs.clear();
		this.repaint();
	}
	
	public ArrayList<Hypercycloid> getGraphs(){
		return this.graphs;
	}
}
