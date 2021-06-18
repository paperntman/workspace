package keyboard;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

public class Main { 
	public static void main(String[] args) throws AWTException, InterruptedException {
		Thread.sleep(1000);
		Robot rob = new Robot();
		for (int i = 0; i < 1000; i++) {
			rob.setAutoDelay(1);
			rob.keyRelease(KeyEvent.VK_2);
			rob.keyPress(KeyEvent.VK_2);
		}
		return;
	}
}
