import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JScrollBar;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.GridLayout;

public class GameSaveMenuGUI extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameSaveMenuGUI frame = new GameSaveMenuGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GameSaveMenuGUI() {
		// Create frame, initial size, border, and grid
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 1, 0, 0));
		
		// Create pause menu label, set font and location
		JLabel lblPauseMenu = new JLabel("PAUSE MENU");
		lblPauseMenu.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblPauseMenu.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblPauseMenu);
		
		// Create resume button, set action listener
		JButton resumeButton = new JButton("RESUME");
		resumeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		contentPane.add(resumeButton);
		
		// Create save button, set action listener
		JButton saveButton = new JButton("SAVE");
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		contentPane.add(saveButton);
		
		// Create options button, set action listener
		JButton optionsButton = new JButton("OPTIONS");
		optionsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		contentPane.add(optionsButton);
		
		// Create quit button, set action listener
		JButton quitButton = new JButton("QUIT");
		quitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		contentPane.add(quitButton);
	}

}
