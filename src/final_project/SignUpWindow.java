package final_project;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class SignUpWindow extends JFrame{

	studyCafeModel model;
	
	JLabel idLabel;
	JTextField inputID;
	JLabel pwLabel;
	JTextField inputPW;
	
	JLabel nameLabel;
	JTextField inputName;
	JLabel phoneNumLabel;
	JTextField inputPhoneNum;
	
	JButton signUpButton;
	
	
	public SignUpWindow(studyCafeModel model) {
		
		// collection of colors and font
		Color skkuGreen = new Color(154, 205, 50);
		Color skkuNavy = new Color(7, 42, 96);
		Color Silver = new Color(211, 211, 211);
		Color lightSilver = new Color(238, 238, 238);

		Font font = new Font("Helvetica", Font.BOLD, 13);
		
		
		// General Settings
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLayout(null);
		setTitle("Sign Up");
		setSize(300, 220);
		
		// To access member's ArrayList 
		// Use when checking ID is already existing and
		// Use when saving new member's info
		this.model = model;

		idLabel = new JLabel();
		idLabel.setBounds(20, 10, 100, 20);
		idLabel.setText("User ID");
		add(idLabel);

		inputID = new JTextField();
		inputID.setBounds(120, 10, 150, 20);
		add(inputID);

		pwLabel = new JLabel();
		pwLabel.setBounds(20, 40, 100, 20);
		pwLabel.setText("PassWord");
		add(pwLabel);

		inputPW = new JTextField();
		inputPW.setBounds(120, 40, 150, 20);
		add(inputPW);

		nameLabel = new JLabel();
		nameLabel.setBounds(20, 70, 100, 20);
		nameLabel.setText("Name");
		add(nameLabel);

		inputName = new JTextField();
		inputName.setBounds(120, 70, 150, 20);
		add(inputName);
		
		phoneNumLabel = new JLabel();
		phoneNumLabel.setBounds(20, 100, 100, 20);
		phoneNumLabel.setText("Phone Number");
		add(phoneNumLabel);

		inputPhoneNum = new JTextField();
		inputPhoneNum.setBounds(120, 100, 150, 20);
		add(inputPhoneNum);

		signUpButton = new JButton();
		signUpButton.setText("Sign Up");
		signUpButton.setForeground(Color.white);
		signUpButton.setBackground(skkuNavy);
		signUpButton.setBounds(100, 140, 100, 30);
		
		// Add ActionListener to Sign up button
		signUpButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				// Flag : If Sign Up is valid -> true
				Boolean isValidSignUp = false;
				
				// Get inputs from JTextfield, jList
				String myID = inputID.getText();
				String myPW = inputPW.getText();
				String myName = inputName.getText();
				String myPhoneNum = inputPhoneNum.getText();
				
				try {
					
					// Check if there is empty input
					if (myID.equals("") || myPW.equals("") || myName.equals("") || myPhoneNum.equals("")) {
						throw new EmptyInputException();
					}
					
					// Check if input of ID already exists
					for (int i = 0; i < model.MemberList.size(); i++) {
						if (model.MemberList.get(i).getID().equals(myID)) {
							throw new ExistingIDException();
						}
					}
					
					// Check if input of phone number is invalid
					if (myPhoneNum.indexOf(" ") != 2 || myPhoneNum.indexOf("-") != 7)
						throw new PhoneNumFormatException();
					
					// Passed all of the Exception handling
					isValidSignUp = true;
					
				} catch (EmptyInputException ex) {
					JOptionPane.showMessageDialog(SignUpWindow.this, "Fill all the blank and Try Again!", "Error",
							JOptionPane.ERROR_MESSAGE);
				} catch (ExistingIDException ex) {
					JOptionPane.showMessageDialog(SignUpWindow.this, "Inserted ID already exists. Try Again!", "Error",
							JOptionPane.ERROR_MESSAGE);
				} catch (PhoneNumFormatException ex) {
					JOptionPane.showMessageDialog(SignUpWindow.this, "Proper format for a phone number is ‘10 2158-0222’", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
				
				// When Sign-up is valid (No exception)
				if (isValidSignUp == true) {
					// Create new member class and append it to member List
					Member<Integer> mem = new Member<Integer>(myName, myPhoneNum, myID, myPW, 0);
					model.MemberList.add(mem);
					
					// Close the sign up window
					dispose();
				}
				
				
			}
		});
		
		add(signUpButton);
		
		setVisible(true);
	}
	
}
