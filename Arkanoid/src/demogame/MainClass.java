package demogame;


import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.text.Caret;

public class MainClass {
	
	public static void popUpWindowAbout() {
		
		String student_name = "Berk";  
		String student_surname = "Ateþalp"; 
		String student_school_num = "20190702052"; 
		String student_email = "berk.atesalp@std.yeditepe.edu.tr"; 
		
		JFrame frame_popup = new JFrame();
		JLabel name_label, name_field, surname_label, surname_field, school_num_label, school_num_field, email_label, email_field;
		
		
		name_label = new JLabel("Name: ");
		name_label.setBounds(50, 30, 100, 30);
		
		name_field = new JLabel();
		name_field.setBounds(100, 30, 100, 30);
		name_field.setText(student_name);
		
		surname_label = new JLabel("Surname: ");
		surname_label.setBounds(50, 70, 100, 30);
		
		surname_field = new JLabel();
		surname_field.setBounds(120, 70, 100, 30);
		surname_field.setText(student_surname);
		
		school_num_label = new JLabel("School Number: ");
		school_num_label.setBounds(50, 100, 100, 30);
		
		school_num_field = new JLabel();
		school_num_field.setBounds(150, 100, 122, 30);
		school_num_field.setText(student_school_num);
		
		email_label = new JLabel("Email: ");
		email_label.setBounds(50, 130, 100, 30);
		
		email_field = new JLabel();
		email_field.setBounds(100, 130, 100, 30);
		email_field.setText(student_email);
		
		frame_popup.add(name_label);
		frame_popup.add(name_field);
		frame_popup.add(surname_label);
		frame_popup.add(surname_field);
		frame_popup.add(school_num_label);
		frame_popup.add(school_num_field);
		frame_popup.add(email_label);
		frame_popup.add(email_field);
		
		frame_popup.setSize(400, 300);
		frame_popup.setLayout(null);
		frame_popup.setVisible(true);
	}
	
	public static void popupWindowHelp() {
		String gameplay_tips = "Arkanoid game is kind of 'Brick Breaker' game "
				+ "and main purpose of the game is break the all blocks "
				+ "while hitting with the ball. The Player can lead "
				+ "the ball while both left and right arrow "
				+ "or dragging the mouse on the game panel. Players earn "
				+ "points when break the blocks and if you clear all "
				+ "blocks then you are eligible to move on to the next level. "
				+ "If you clear all levels in the game "
				+ "then you can reach the highest score and get the Winner title!!";
		final JPanel panel = new JPanel();
		JFrame frame_about = new JFrame();
		
		JTextArea about_label = new JTextArea(
				gameplay_tips, 
                10, 
                30);
		
		about_label.setFont(about_label.getFont().deriveFont(18.0f));
		about_label.setLineWrap(true);
		about_label.setWrapStyleWord(true);
		about_label.setOpaque(false);
		about_label.setEditable(false);
		
		
		panel.add(about_label);
		frame_about.add(panel);
		frame_about.pack();
		
		frame_about.setVisible(true);
	}
	
	public static void selectDifficulties() {
		
				
				
				JFrame option_diff = new JFrame();
				JLabel option_title;
				
				option_title = new JLabel("Select Difficulties ");
				option_title.setBounds(215, 140, 300, 30);
				option_title.setFont(option_title.getFont().deriveFont(26.0f));
				
				// level 1 Selection
				
				JButton level_one = new JButton("Level - 1");
				level_one.setBounds(250,250,150,30);
				
				level_one.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						openGamePanel(1);
					}
				});
				
				// level 2 Selection
				
				JButton level_two = new JButton("Level - 2");
				level_two.setBounds(250,325,150,30);
				
				level_two.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						openGamePanel(2);
					}
				});
				
				// level 3 Selection
				
				JButton level_three = new JButton("Level - 3");
				level_three.setBounds(250,400,150,30);
				
				level_three.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						openGamePanel(3);
					}
				});
				
				option_diff.add(level_one);
				option_diff.add(level_two);
				option_diff.add(level_three);
				option_diff.add(option_title);
				
				option_diff.setSize(700, 650);
				option_diff.setLayout(null);
				option_diff.setVisible(true);
				
	}
	
	public static void openScorePanel(ArrayList<Integer> arr) {
		JFrame frame_score = new JFrame();
		JLabel score_title;
		
		score_title = new JLabel("Top 10 Scores ");
		score_title.setBounds(120, 30, 150, 30);
		score_title.setFont(score_title.getFont().deriveFont(18.0f));
		
		frame_score.add(score_title);
		
		JLabel[] score_labels = new JLabel[10];
		// Fill that array with your JLables

		int heightGrowth = 20;
		for (int i = 0; i < (arr.size() < 10 ? arr.size() : 10); i++) {
			score_labels[i] = new JLabel();
			score_labels[i].setText((i+1) + " -  " + arr.get(i));
			score_labels[i].setBounds(150, 60 + heightGrowth, 100, 30);
			score_labels[i].setFont(score_labels[i].getFont().deriveFont(13.0f));
			frame_score.add(score_labels[i]);
			heightGrowth += 20;
		}
		
		frame_score.setSize(400, 400);
		frame_score.setLayout(null);
		frame_score.setVisible(true);
	}
	
	public static void openGamePanel(int desired_level) {
		GamePlay gamePlay = new GamePlay(desired_level);
		JFrame frame_obj = new JFrame();

		frame_obj.add(gamePlay);
		frame_obj.setTitle("Arkanoid");
		frame_obj.setSize(700, 600);
		frame_obj.setLocationRelativeTo(null);
		frame_obj.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		frame_obj.setResizable(false);
		frame_obj.setVisible(true);
	}
	
	public void loadMainMenu() {
		
		int PanelWidth = 700;
		int PanelHeigth = 650;
		
		EndPanel endPanel = new EndPanel();
		JFrame fmenu = new JFrame("Akanoid Menu");
		
		// New Game Buttonu
		
		JButton newgame_btn = new JButton("New Game");
		newgame_btn.setBounds(250,100,150,30);
		
		newgame_btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				openGamePanel(1);
				fmenu.setVisible(false);			
			}
		});
		
		// Option Buttonu
		
		JButton option_btn = new JButton("Options");
		option_btn.setBounds(250,175,150,30);
		
		option_btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				selectDifficulties();
				
			}
		});
		
		// Score Buttonu
		
		JButton score_btn = new JButton("Scores");
		score_btn.setBounds(250,250,150,30);
		
		score_btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					ArrayList<Integer> ordered_score_list = endPanel.getScores();
					openScorePanel(ordered_score_list);
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		// Help Buttonu
		
		JButton help_btn = new JButton("Help");
		help_btn.setBounds(250,325,150,30);
		
		help_btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				popupWindowHelp();
			}
		});
		
		// About Buttonu
		
		JButton about_btn = new JButton("About");
		about_btn.setBounds(250,400,150,30);
		
		about_btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				popUpWindowAbout();
			}
		});
		
		// Exit Buttonu
		
		JButton exit_btn = new JButton("Exit");
		exit_btn.setBounds(250,475,150,30);
				
		exit_btn.addActionListener(new ActionListener() {
					
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);	
				
			}
		});		
		
		fmenu.add(newgame_btn);
		fmenu.add(option_btn);
		fmenu.add(score_btn);
		fmenu.add(help_btn);
		fmenu.add(about_btn);
		fmenu.add(exit_btn);
		
		fmenu.setSize(PanelWidth, PanelHeigth);
		fmenu.setLayout(null);
		fmenu.setVisible(true);
		
	}

	public static void main(String[] args) {
		
		MainClass mainClass = new MainClass();
		mainClass.loadMainMenu();
		
	}

}

