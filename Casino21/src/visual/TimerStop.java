package visual;

import javax.swing.Timer;

public class TimerStop {
	private Timer timer;
	private int repetitions = 0; 
	
	
	public void stopTimer() {
		timer.stop();
	}
	public int getRepetitions() {
		return repetitions;
	}
	public void incrementRepetitions() {
		repetitions++;
	}
	public void setTimer(Timer timer) {
		this.timer = timer;
	}
	public void startTimer() {
		timer.start();
	}
	public void setStep(int step) {
		timer.setDelay(step);
	}
}
