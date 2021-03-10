package cmd_log;

import java.awt.EventQueue;

import gui_classes.MainWindow;

public class Main {

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				MainWindow mainWindow = new MainWindow();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
	
}
