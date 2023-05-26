package final_project;

public class Main {
	public static void main(String[] args) {
		studyCafeView view = new studyCafeView();
		studyCafeModel model = new studyCafeModel();
		
		Member<Integer> mem1 = new Member<Integer>("David", "10 5243-2617", "nicedavid98", "nicebbc6266!", 0);
		Member<Integer> mem2 = new Member<Integer>("Sally", "10 1291-3289", "nicesally98", "1234", 0);
		Member<Integer> mem3 = new Member<Integer>("James", "10 4820-1281", "nicejames98", "1234", 0);
		Member<Integer> mem4 = new Member<Integer>("Sam", "10 1281-0818", "nicesam98", "1234", 0);
		Member<Integer> mem5 = new Member<Integer>("Amy", "10 1829-4482", "niceamy98", "1234", 0);
		
		model.MemberList.add(mem1);
		model.MemberList.add(mem2);
		model.MemberList.add(mem3);
		model.MemberList.add(mem4);
		model.MemberList.add(mem5);
		
		studyCafeController controller = new studyCafeController(model, view);
	}
}
