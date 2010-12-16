package test;

import graphics.JSimpleTerminalPanel;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;

import network.ssh.SimpleSSHClient;


public class TestJSimpleTerminalPanel {
	
	public static String requestLogin(String message) {
		return JOptionPane.showInputDialog(message, System.getProperty("user.name") + "@localhost");
	}
	
	public static String requestPassword(String message) {
		JPasswordField passwordField = new JPasswordField();
		int result = JOptionPane.showConfirmDialog(null, new Object[] {passwordField}, message, JOptionPane.OK_CANCEL_OPTION);
		
		if (result == JOptionPane.CANCEL_OPTION) return null;
		
		return new String(passwordField.getPassword());
	}
	
	
	public static void main(String[] args) throws Throwable {
		String login = requestLogin("Enter username@hostname");
		if (login == null) System.exit(0);
		String password = requestPassword("Your password");
		if (password == null) System.exit(0);
		
		SimpleSSHClient sshClient = new SimpleSSHClient(login, password);
		sshClient.connect();
		
		
		JFrame frame = new JFrame();
		frame.setLayout(new BorderLayout());
		
		JSimpleTerminalPanel terminal = new JSimpleTerminalPanel(sshClient);
		frame.add(new JScrollPane(terminal));
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
	
}