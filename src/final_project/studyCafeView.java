package final_project;

import java.util.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class studyCafeView extends JFrame {

	// Green Panel that is on the top
	JPanel abovePanel;
	// Panel that contains seatStatusPanel and inputPanel
	JPanel mainPanel;
	// Panel that shows seat status
	JPanel seatStatusPanel;
	// Panel that user can input id, pw, seat number ...
	JPanel inputPanel;

	// Components of abovePanel
	int numOfRemainingSeat; // number of remaining seat that displays above
	JLabel branchLabel;
	JLabel leftSeatLabel;
	JLabel occupiedLabel;
	JTextField branchNameArea;
	JTextField leftSeatNumArea;
	JTextField occupiedNumArea;
	JButton printProfileButton;
	JButton exitButton;

	// Components of inputPanel
	JScrollPane scrollPane;
	JList paymentPlanList;
	ArrayList<String> listData = new ArrayList<String>(); // ArrayList that contains JList's menus
	DefaultListModel<String> listModel = new DefaultListModel<String>(); // List that make up JList

	JLabel idLabel;
	JTextField inputID;
	JLabel pwLabel;
	JTextField inputPW;
	JLabel seatNumLabel;
	JTextField inputSeatNum;
	JButton logInButton;
	JButton signUpButton;

	// Components of seatStatusPanel
	String seatStatusTexts[]; // String that will displayed in each seat status Label
	JTextPane[] seatTextPanes;
	JPanel[] seats;
	JButton logOutButtons[];

	public studyCafeView() {

		// collection of colors and font
		Color skkuGreen = new Color(154, 205, 50);
		Color skkuNavy = new Color(7, 42, 96);
		Color Silver = new Color(211, 211, 211);
		Color lightSilver = new Color(238, 238, 238);

		Font font = new Font("Helvetica", Font.BOLD, 13);

		// General Settings
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLayout(null);
		setTitle("Study Cafe Management System");
		setSize(977, 600);

		// Setting abovePanel
		abovePanel = new JPanel();
		abovePanel.setLayout(null);
		abovePanel.setBackground(skkuGreen);
		abovePanel.setBounds(0, 0, 1000, 40);

		// Setting components of abovePanel
		numOfRemainingSeat = 20;

		branchLabel = new JLabel();
		branchLabel.setText("Branch");
		branchLabel.setFont(font);
		branchLabel.setBounds(30, 10, 100, 15);
		abovePanel.add(branchLabel);

		branchNameArea = new JTextField();
		branchNameArea.setText("SKKU Study Cafe");
		branchNameArea.setFont(font);
		branchNameArea.setHorizontalAlignment(JLabel.CENTER);
		branchNameArea.setFocusable(false);
		branchNameArea.setBackground(Color.WHITE);
		branchNameArea.setBounds(85, 5, 200, 27);
		abovePanel.add(branchNameArea);

		leftSeatLabel = new JLabel();
		leftSeatLabel.setText("Remaining");
		leftSeatLabel.setFont(font);
		leftSeatLabel.setBounds(310, 10, 100, 15);
		abovePanel.add(leftSeatLabel);

		leftSeatNumArea = new JTextField();
		leftSeatNumArea.setText("" + numOfRemainingSeat);
		leftSeatNumArea.setFont(font);
		leftSeatNumArea.setHorizontalAlignment(JLabel.CENTER);
		leftSeatNumArea.setFocusable(false);
		leftSeatNumArea.setBackground(Color.WHITE);
		leftSeatNumArea.setBounds(385, 5, 70, 27);
		abovePanel.add(leftSeatNumArea);

		occupiedLabel = new JLabel();
		occupiedLabel.setText("Occupied");
		occupiedLabel.setFont(font);
		occupiedLabel.setBounds(485, 10, 100, 15);
		abovePanel.add(occupiedLabel);

		occupiedNumArea = new JTextField();
		occupiedNumArea.setText("" + (20 - numOfRemainingSeat));
		occupiedNumArea.setFont(font);
		occupiedNumArea.setHorizontalAlignment(JLabel.CENTER);
		occupiedNumArea.setFocusable(false);
		occupiedNumArea.setBackground(Color.WHITE);
		occupiedNumArea.setBounds(555, 5, 70, 27);
		abovePanel.add(occupiedNumArea);

		printProfileButton = new JButton();
		printProfileButton.setText("Print Profile");
		printProfileButton.setBackground(skkuNavy);
		printProfileButton.setForeground(Color.white);
		printProfileButton.setBounds(670, 5, 130, 27);
		abovePanel.add(printProfileButton);

		exitButton = new JButton();
		exitButton.setText("Exit");
		exitButton.setBackground(skkuNavy);
		exitButton.setForeground(Color.white);
		exitButton.setBounds(830, 5, 100, 27);
		abovePanel.add(exitButton);

		// Setting mainPanel (contains seatStatusPanel & inputPanel)
		mainPanel = new JPanel();
		mainPanel.setBackground(Color.white);
		mainPanel.setLayout(null);
		mainPanel.setBounds(5, 45, 950, 510);

		// Setting seatStatusPanel
		seatStatusPanel = new JPanel();
		seatStatusPanel.setLayout(new GridLayout(4, 5, 10, 10));
		seatStatusPanel.setBackground(lightSilver);
		seatStatusPanel.setBounds(15, 20, 600, 470);

		seatStatusTexts = new String[20];
		for (int i = 0; i < 20; i++) {
			seatStatusTexts[i] = "Empty Seat";
		}
		seatTextPanes = new JTextPane[20];
		for (int i = 0; i < 20; i++) {
			seatTextPanes[i] = new JTextPane();
			seatTextPanes[i].setBounds(7, 5, 100, 70);
			seatTextPanes[i].setFont(font);
			seatTextPanes[i].setText(seatStatusTexts[i]);
			seatTextPanes[i].setBackground(Silver);
			seatTextPanes[i].setFocusable(false);

			// Center Align the text
			StyledDocument doc = seatTextPanes[i].getStyledDocument();
			SimpleAttributeSet center = new SimpleAttributeSet();
			StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
			doc.setParagraphAttributes(0, doc.getLength(), center, false);
		}

		logOutButtons = new JButton[20];
		for (int i = 0; i < 20; i++) {
			logOutButtons[i] = new JButton();
			logOutButtons[i].setBounds(18, 80, 77, 23);
			logOutButtons[i].setText("Log out");
			logOutButtons[i].setBackground(skkuNavy);
			logOutButtons[i].setForeground(Color.white);
		}

		seats = new JPanel[20];
		for (int i = 0; i < 20; i++) {
			seats[i] = new JPanel();
			seats[i].setLayout(null);
			seats[i].setBackground(Silver);
			seats[i].add(seatTextPanes[i]);
			seats[i].add(logOutButtons[i]);
			seatStatusPanel.add(seats[i]);
		}

		// Setting inputPanel
		inputPanel = new JPanel();
		inputPanel.setLayout(null);
		inputPanel.setBackground(Silver);
		inputPanel.setBounds(630, 20, 300, 300);

		idLabel = new JLabel();
		idLabel.setBounds(20, 10, 100, 20);
		idLabel.setText("User ID");
		inputPanel.add(idLabel);

		inputID = new JTextField();
		inputID.setBounds(120, 10, 150, 20);
		inputPanel.add(inputID);

		pwLabel = new JLabel();
		pwLabel.setBounds(20, 40, 100, 20);
		pwLabel.setText("PassWord");
		inputPanel.add(pwLabel);

		inputPW = new JTextField();
		inputPW.setBounds(120, 40, 150, 20);
		inputPanel.add(inputPW);

		seatNumLabel = new JLabel();
		seatNumLabel.setBounds(20, 70, 100, 20);
		seatNumLabel.setText("Seat Number");
		inputPanel.add(seatNumLabel);

		inputSeatNum = new JTextField();
		inputSeatNum.setBounds(120, 70, 150, 20);
		inputPanel.add(inputSeatNum);

		for (String menu : paymentPlans.rawData) {
			listData.add(menu);
		} // List that contains JList's menus
		listModel.addAll(listData);
		paymentPlanList = new JList<String>(listModel);
		scrollPane = new JScrollPane(paymentPlanList);
		scrollPane.setBounds(20, 100, 260, 150);
		inputPanel.add(scrollPane);

		logInButton = new JButton();
		logInButton.setText("Log In");
		logInButton.setForeground(Color.white);
		logInButton.setBackground(skkuNavy);
		logInButton.setBounds(30, 260, 100, 30);
		inputPanel.add(logInButton);
		
		signUpButton = new JButton();
		signUpButton.setText("Sign Up");
		signUpButton.setForeground(Color.white);
		signUpButton.setBackground(skkuNavy);
		signUpButton.setBounds(170, 260, 100, 30);
		inputPanel.add(signUpButton);

		// Add inputPanel & seatStatusPanel to mainPanel
		mainPanel.add(inputPanel);
		mainPanel.add(seatStatusPanel);

		// Add mainPanel & abovePanel to JFrame
		add(mainPanel);
		add(abovePanel);

		// Set visible
		setVisible(true);

	}

	// Function to add ActionListener
	public void setActionListener(ActionListener listener) {
		
		exitButton.addActionListener(listener);
		logInButton.addActionListener(listener);
		signUpButton.addActionListener(listener);
		printProfileButton.addActionListener(listener);
		printProfileButton.addActionListener(listener);
		
		for (int i = 0; i < 20; i++) {
			logOutButtons[i].addActionListener(listener);
		}
	}

}
