import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Window {
	private static int panelID = 0;
	
	private static Map<JPanel,String> panelMap = new HashMap<JPanel,String>();
	
	private static JFrame frame;
	private static JComboBox<String> idSelector;
	
	public static void main(String args[]) {
		frame = new JFrame("Timer manipulation");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel buttonPanel = new JPanel();
		JButton addbtn = new JButton("add timer");
		JLabel idLbl = new JLabel("id:");
		idSelector = new JComboBox<String>();
		JButton rmvbtn = new JButton("rmv timer");

		addbtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				TimerLabel tempp = new TimerLabel(panelID);
				
				JPanel p = new JPanel();
				p.add(tempp.getLabel());
				
				try {
					addPanel(Integer.toString(panelID), p);
				}
				catch(Exception exc) {
					JOptionPane.showMessageDialog(frame, exc.getLocalizedMessage(), "Add Exception", JOptionPane.WARNING_MESSAGE);
				}
			}
			
		});
		
		rmvbtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				try {
					removePanel((String)idSelector.getSelectedItem());
				}
				catch(Exception exc) {
					JOptionPane.showMessageDialog(frame, exc.getLocalizedMessage(), "Remove Exception", JOptionPane.WARNING_MESSAGE);
				}
			}
			
		});
		
		buttonPanel.add(addbtn);
		buttonPanel.add(idLbl);
		buttonPanel.add(idSelector);
		buttonPanel.add(rmvbtn);
		
		frame.add(buttonPanel);
		
		TimerLabel tempp = new TimerLabel(panelID);
		
		JPanel p = new JPanel();
		p.add(tempp.getLabel());
		
		addPanel(Integer.toString(panelID), p);
		
		frame.setSize(500,500);
		frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
		frame.setVisible(true);
	}
	
	public static void addPanel(String id, JPanel panel) {
		if (id == null || panel == null) throw new NullPointerException("addPanel; " + (id==null && panel==null ? "id and panel " : (id==null ? "id " : "panel ") ) + "is null");
		else if (panelMap.containsValue(id)) throw new IllegalArgumentException("addPanel; ID already in map: \nID: " + id);
		
		panelMap.put(panel, id);
		frame.add(panel);
		panelID++;
		
		idSelector.addItem(id);
	}
	
	public static JPanel removePanel(String id) {
		if (id == null) throw new NullPointerException("rmvPanel; id is null");
		else if (!panelMap.containsValue(id)) throw new IllegalArgumentException("rmvPanel; ID not in map: \nID: " + id);
		
		for (JPanel p : panelMap.keySet()) {
			if (panelMap.get(p).equals(id)) {
				panelMap.remove(p);
				
				frame.remove(p);
				
				idSelector.removeItem(id);
				return p;
			}
		}
		
		return null;
		
	}
	
}
