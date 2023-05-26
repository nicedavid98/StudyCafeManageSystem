package final_project;

public class Main {
	public static void main(String[] args) {
		studyCafeView view = new studyCafeView();
		studyCafeModel model = new studyCafeModel();
		
		Member<Integer> mem1 = new Member<Integer>("David", "010-5243-2617", "07/11/1998", "nicedavid98", "nicebbc6266!", 0);
		Member<Integer> mem2 = new Member<Integer>("Sally", "010-1291-3289", "05/10/1998", "nicesally98", "1234", 0);
		Member<Integer> mem3 = new Member<Integer>("James", "010-4820-1281", "01/07/2000", "nicejames00", "1234", 0);
		Member<Integer> mem4 = new Member<Integer>("Sam", "010-1281-0818", "03/12/2004", "nicesam04", "1234", 0);
		Member<Integer> mem5 = new Member<Integer>("Amy", "010-1829-4482", "08/01/2005", "niceamy05", "1234", 0);
		
		model.MemberList.add(mem1);
		model.MemberList.add(mem2);
		model.MemberList.add(mem3);
		model.MemberList.add(mem4);
		model.MemberList.add(mem5);
		
		studyCafeController controller = new studyCafeController(model, view);
	}
}
