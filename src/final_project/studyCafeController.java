package final_project;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

public class studyCafeController implements ActionListener {

	private studyCafeView view;
	private studyCafeModel model;

	// Array of Threads, each member of this cafe will have 1 Thread
	// Member who is in Index x of the model.MemberList will get workers[x] thread
	ubdateWorker workers[];

	// Constructor
	public studyCafeController(studyCafeModel model, studyCafeView view) {

		this.model = model;
		this.view = view;

		// Set ActionListener to Buttons
		this.view.setActionListener(this);

		// Initialize thread
		//workers = new ubdateWorker[model.MemberList.size()];
		workers = new ubdateWorker[100];

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		// Actions when clicking Exit Button
		if (e.getSource() == view.exitButton) {
			// Stop all workers(Threads) that exists
			for (int i = 0; i < model.MemberList.size(); i++) {
				if (workers[i] != null)
					workers[i].stopWork();
			}
			System.exit(0);
		}

		// Actions when clicking SignUp Button
		if (e.getSource() == view.signUpButton) {
			SignUpWindow signUpWindow = new SignUpWindow(this.model);
		}

		// Actions when clicking Log out Buttons
		// Checking all 20 buttons
		for (int i = 0; i < 20; i++) {
			if (e.getSource() == view.logOutButtons[i]) {
				// Finding the worker (Thread) to stop
				for (int j = 0; j < model.MemberList.size(); j++) {
					if (workers[j] != null) {
						if (workers[j].mySeat == i) {
							
							// Update number of remaining seat
							view.numOfRemainingSeat++;
							// Update number of remaining seat in GUI (Above Green Panel)
							view.leftSeatNumArea.setText("" + view.numOfRemainingSeat);
							view.occupiedNumArea.setText("" + (20 - view.numOfRemainingSeat));
							
							workers[j].stopWork();
							// Delete the initialized worker(Thread)
							// Because we determine whether certain seat is empty or occupied by searching worker class
							workers[j] = null;
						}
					}
				}
			}
		}

		// Actions when clicking Print Profile Button
		// It generates .txt file containing all member's info
		if (e.getSource() == view.printProfileButton) {

			// Making a file Stream to write the Member's Information(Profile) in file
			FileOutputStream fileObject;
			try {
				// No appending to file, just overwrite
				fileObject = new FileOutputStream("Members_Info.txt", false);
				PrintWriter x = new PrintWriter(fileObject);

				// Save member's info by for loop
				for (int i = 0; i < model.MemberList.size(); i++) {
					String text = model.MemberList.get(i).getName() + " " + model.MemberList.get(i).getPhoneNum() + " "
							+ model.MemberList.get(i).getBirthDate() + " " + model.MemberList.get(i).getID() + " "
							+ model.MemberList.get(i).getPW() + " " + model.MemberList.get(i).getLeftTime() + "\n";
					x.print(text);
				}
				x.close();
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}

		// Actions when clicking Log in Button
		if (e.getSource() == view.logInButton) {

			// Flag : If log in is valid -> true
			Boolean isValidLogIn = false;

			// Get inputs from JTextfield, jList
			String myID = view.inputID.getText();
			String myPW = view.inputPW.getText();
			String mySeat = view.inputSeatNum.getText();
			int myPaymentPlan = view.paymentPlanList.getSelectedIndex();

			// Erase the JTextfield after clicking log in button
			view.inputID.setText("");
			view.inputPW.setText("");
			view.inputSeatNum.setText("");

			try {

				// Index for finding and matching ID & PW
				int index = 0;

				// Check if there is empty input
				if (myID.equals("") || myPW.equals("") || mySeat.equals("")) {
					throw new EmptyInputException();
				}

				// Check if the seat Number that user inputed is Integer
				// Automatically throw NumberFormatException if the input is invalid
				Integer.parseInt(mySeat);

				// Check if the user selected already occupied seat
				for (int i = 0; i < model.MemberList.size(); i++) {
					if (workers[i] != null) {
						if (Integer.parseInt(mySeat) == workers[i].mySeat) {
							throw new OccupiedSeatException();
						}
					}
				}

				// Check ID and PW
				for (index = 0; index < model.MemberList.size(); index++) {
					// If input Id is existing, check PW
					if (model.MemberList.get(index).ID.equals(myID)) {
						if (model.MemberList.get(index).PW.equals(myPW)) {
							
							// Check if the user have no remaining time but selected "Use remaining time"
							if (myPaymentPlan == 0 && model.MemberList.get(index).leftTime == 0) {
								throw new NoTimeLeftException();
							}
							
							// Check if the user made duplicate login
							if (workers[index] != null)
								throw new DuplicateLoginException();

							// Succeed
							isValidLogIn = true;
							JOptionPane.showMessageDialog(view,
									"log-In succeed, Please insert Payment method if necessary!", "Notice",
									JOptionPane.INFORMATION_MESSAGE);
							break;

						}
						// If ID is existing but not matches to PW
						throw new InvalidLogInException();
					}
					// If input Id is not existing
					if (index == model.MemberList.size() - 1) {
						throw new InvalidLogInException();
					}
				}

			} catch (EmptyInputException ex) {
				JOptionPane.showMessageDialog(view, "Fill all the blank and Try Again!", "Error",
						JOptionPane.ERROR_MESSAGE);
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(view, "Seat Number must be Integer. Try Again!", "Error",
						JOptionPane.ERROR_MESSAGE);
			} catch (OccupiedSeatException ex) {
				JOptionPane.showMessageDialog(view, "Selected seat is already occupied. Try Again!", "Error",
						JOptionPane.ERROR_MESSAGE);
			} catch (InvalidLogInException ex) {
				JOptionPane.showMessageDialog(view, "Invalid ID or Unmatching PW. Try Again!", "Error",
						JOptionPane.ERROR_MESSAGE);
			} catch (NoTimeLeftException ex) {
				JOptionPane.showMessageDialog(view, "You have to recharge your remaining time", "Error",
						JOptionPane.ERROR_MESSAGE);
			} catch (DuplicateLoginException ex) {
				JOptionPane.showMessageDialog(view, "Duplicate login. Cannot Log-In twice", "Error",
						JOptionPane.ERROR_MESSAGE);
			}

			// When log-in succeed
			if (isValidLogIn == true) {

				int myIndex = 0; // log-in succeed, user's index will be saved here

				// Finding user's index
				for (int i = 0; i < model.MemberList.size(); i++) {
					if (model.MemberList.get(i).ID.equals(myID))
						myIndex = i;
				}

				// Update number of remaining seat
				view.numOfRemainingSeat--;
				// Update number of remaining seat in GUI (Above Green Panel)
				view.leftSeatNumArea.setText("" + view.numOfRemainingSeat);
				view.occupiedNumArea.setText("" + (20 - view.numOfRemainingSeat));

				// Add user's remaining time according to selected PaymentPlan
				if (myPaymentPlan == 0) {
					model.MemberList.get(myIndex).leftTime += 0;
				} else if (myPaymentPlan == 1) {
					model.MemberList.get(myIndex).leftTime += 60;
				} else if (myPaymentPlan == 2) {
					model.MemberList.get(myIndex).leftTime += 120;
				} else if (myPaymentPlan == 3) {
					model.MemberList.get(myIndex).leftTime += 180;
				} else if (myPaymentPlan == 4) {
					model.MemberList.get(myIndex).leftTime += 300;
				} else if (myPaymentPlan == 5) {
					model.MemberList.get(myIndex).leftTime += 420;
				} else if (myPaymentPlan == 6) {
					model.MemberList.get(myIndex).leftTime += 600;
				} else if (myPaymentPlan == 7) {
					model.MemberList.get(myIndex).leftTime += 1440;
				}

				// Member who is in Index x of the model.MemberList will get workers[x] thread
				// The member who successfully logged-in will start his or her thread right now
				workers[myIndex] = new ubdateWorker(myIndex, Integer.parseInt(mySeat));
				workers[myIndex].execute();

			}

		}

	}

	// Thread
	public class ubdateWorker extends SwingWorker<Integer, Integer> {

		int myIndex; // The index of user who are using this thread
		int mySeat; // The current seat position of user who are using this thread
		boolean stopped; // Determines whether the thread will stop

		// Constructor
		public ubdateWorker(int myIndex, int mySeat) {
			this.myIndex = myIndex;
			this.mySeat = mySeat;
			this.stopped = false;
		}

		@Override
		protected Integer doInBackground() throws Exception {
			while (stopped == false) {

				// In this program, it is arbitrarily set
				// so that one repetition of the while loop means one minute.
				// I can modify this value to make the time seems to run faster
				Thread.sleep(200);

				// Every repetition of the while loop, the time left for user decreases
				model.MemberList.get(myIndex).leftTime--;

				// Every repetition of the while loop, the seat panel in GUI will be updated
				String text = "Occupied\n" + model.MemberList.get(myIndex).name + "\n"
						+ model.MemberList.get(myIndex).leftTime + " min left";
				view.seatTextPanes[mySeat].setText(text);

				// If this while loop ends as the user spend all time remaining
				if (model.MemberList.get(myIndex).leftTime <= 0) {
					view.seatTextPanes[mySeat].setText("Empty Seat");
					
					// Update number of remaining seat
					view.numOfRemainingSeat++;
					// Update number of remaining seat in GUI (Above Green Panel)
					view.leftSeatNumArea.setText("" + view.numOfRemainingSeat);
					view.occupiedNumArea.setText("" + (20 - view.numOfRemainingSeat));
					
					stopWork();
					workers[myIndex] = null;
				}
			}
			// If this while loop ends as the user log out
			if (stopped == true) {
				view.seatTextPanes[mySeat].setText("Empty Seat");
			}
			return 0;
		}

		// Set stopped as true, the very next while loop will break.
		public void stopWork() {
			stopped = true;
		}
	}

}
