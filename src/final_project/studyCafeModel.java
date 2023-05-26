package final_project;

import java.util.ArrayList;

public class studyCafeModel {
	
	// ArrayList to save Customers info
	// Used Polymorphism (Person -> customer)
	ArrayList<Member<Integer>> MemberList;

	public studyCafeModel() {
		super();
		MemberList = new ArrayList<Member<Integer>>();
	}
	
}
