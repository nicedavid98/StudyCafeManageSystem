package final_project;

// Class that saves info about Customer(Member) of study cafe
// Inherited Person class
// Used Generic class
public class Member<T extends Number> extends Person {

	String ID;
	String PW;
	T leftTime;
	
	public Member(String name, String phoneNum, String id, String pw, T leftTime) {
		this.name = name;
		this.phoneNum = phoneNum;
		this.ID = id;
		this.PW = pw;
		this.leftTime = leftTime;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getPW() {
		return PW;
	}

	public void setPW(String pW) {
		PW = pW;
	}

	public T getLeftTime() {
		return leftTime;
	}

	public void setLeftTime(T leftTime) {
		this.leftTime = leftTime;
	}
}
