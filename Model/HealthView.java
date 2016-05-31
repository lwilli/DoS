package Model;

/*Just use a customize Label to set up the health system*/
public class HealthView {
	public enum Grade {
	    A, B, C, D, F;
	}
    private Grade grade;

    public HealthView() {
        grade = Grade.A;
    }

    public String getGrade() {
        switch (this.grade) {
            case A:
                return "A";
            case B:
                return "B";
            case C:
                return "C";
            case D:
                return "D";
            case F:
                return "F";
        }

        return "";
    }

    public void setGrade(String gr) {
    	
    	if (gr.equals("A"))
    		this.grade = Grade.A;
    	if (gr.equals("B"))
    		this.grade = Grade.B;
    	if (gr.equals("C"))
    		this.grade = Grade.C;
    	if (gr.equals("D"))
    		this.grade = Grade.D;
    	if (gr.equals("F"))
    		this.grade = Grade.F;
    }

    public void increaseHealth() {
        switch (this.grade) {
            case A: // you cannot increase beyond A
                break;
            case B:
                this.grade = Grade.A;
                break;
            case C:
                this.grade = Grade.B;
                break;
            case D:
                this.grade = Grade.C;
                break;
            case F:
                this.grade = Grade.D;
                break;
        }
    }

    public void decreaseHealth() {
        switch (this.grade) {
            case A: // you cannot increase beyond A
                this.grade = Grade.B;
                break;
            case B:
                this.grade = Grade.C;
                break;
            case C:
                this.grade = Grade.D;
                break;
            case D:
                this.grade = Grade.F;
                break;
            case F:
                this.grade = Grade.F;
                break;
        }
    }
}
