package DoS;
/**
 * Created by MASTER on 4/30/16.
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;


enum Grade {
    A, B, C, D, F;
}

/*Just use a customize Label to set up the health system*/
public class HealthView {
    private JLabel label;
    private Grade grade;

    public HealthView(JLabel label) {
        this.label = label;
        grade = Grade.A;
        this.label.setText("A");
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

    public void setGrade(Grade gr) {
        this.grade = gr;
    }

    public void increaseHealth() {
        switch (this.grade) {
            case A: // you cannot increase beyond A
                label.setText("A");
                break;
            case B:
                label.setText("A");
                this.grade = Grade.A;
                break;
            case C:
                label.setText("B");
                this.grade = Grade.B;
                break;
            case D:
                label.setText("C");
                this.grade = Grade.C;
                break;
            case F:
                label.setText("D");
                this.grade = Grade.D;
                break;
        }
    }

    public void decreaseHealth() {
        switch (this.grade) {
            case A: // you cannot increase beyond A
                label.setText("B");
                this.grade = Grade.B;
                break;
            case B:
                label.setText("C");
                this.grade = Grade.C;
                break;
            case C:
                label.setText("D");
                this.grade = Grade.D;
                break;
            case D:
                label.setText("F");
                this.grade = Grade.F;
                break;
            case F:
                label.setText("F"); //obviously same grade
                this.grade = Grade.F;
                break;
        }
    }
}
