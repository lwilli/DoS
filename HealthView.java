package DoS;
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
    	switch (gr) {
        	case "A":
        		this.grade = Grade.A;
        	case "B":
        		this.grade = Grade.B;
        	case "C":
        		this.grade = Grade.C;
        	case "D":
        		this.grade = Grade.D;
        	case "F":
        		this.grade = Grade.F;
    	}
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
