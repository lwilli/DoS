import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.BoxLayout;

public class MainMenuGUI extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMenuGUI frame = new MainMenuGUI();
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
	public MainMenuGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblMainMenuLabel = new JLabel("DEFENSE OF STALEY");
		lblMainMenuLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblMainMenuLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblMainMenuLabel);
		
		JButton newGameButton = new JButton("NEW GAME");
		newGameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		contentPane.add(newGameButton);
		
		JButton loadGameButton = new JButton("LOAD GAME");
		loadGameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		contentPane.add(loadGameButton);
		
		JButton tutorialButton = new JButton("TUTORIAL");
		tutorialButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		contentPane.add(tutorialButton);
		
		JButton settingsButton = new JButton("SETTINGS");
		settingsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		contentPane.add(settingsButton);
		
		JButton exitButton = new JButton("EXIT");
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		contentPane.add(exitButton);
	}

}
