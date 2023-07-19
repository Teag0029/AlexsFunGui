import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JLabel;

public class TimerLabel {
	private JLabel l = new JLabel();
	private Timer t;
	private int id;
	
	private long startTime = System.currentTimeMillis();
	
	private boolean cancelTimer = false;
	
	public TimerLabel(int id) {
		this.id = id;
		t = new Timer();
		t.scheduleAtFixedRate(task(), 0, 100);
	}
	
	private TimerTask task() {
		return new TimerTask() {
			@Override
			public void run() {
				//don't need a sentinel, will get wiped when animation ends
				if (!cancelTimer) {
					l.setText(Integer.toString(id) + " | Time: " + Double.toString((double)(System.currentTimeMillis() - startTime)/1000) + "s");
				}
				else
					cancel();
					return;
			}
		};
	}
	
	public JLabel getLabel(){
		return l;
	}
}
