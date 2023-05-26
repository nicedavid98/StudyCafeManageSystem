package final_project;

// Class that saves info about person
public class Person {
	
	String name;
	String phoneNum;
	String birthDate;
	
	public Person(String name, String phoneNum, String birthDate) {
		super();
		this.name = name;
		this.phoneNum = phoneNum;
		this.birthDate = birthDate;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public String getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	
}
