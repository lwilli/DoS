import oracle.jrockit.jfr.JFR;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.Timer;

import javax.swing.JLabel;
import javax.swing.GroupLayout;

/*
To start TimerView include a JLabel in the parameters
@timer is the timer object
@startTime always starts at zero
@label the user has to set up the label
@endtime is to be set up at 3000 secs 5 minutes
@finish a boolean to declare when to stop the timer
 */
public class TimerView {
    private Timer timer;
    private int minutes;
    private int startTime;
    private int endTime;
    private JLabel label;
    private boolean finish = false;

    public TimerView (JLabel label) {
        this.minutes = 0;
        this.startTime = 0;
        this.endTime = 300;
        this.label = label;
    }

    /*
    set up the timer Object to 3000 seconds
     */
    public void startTimer() {
        this.timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startTime += 1;
                int rem = startTime % 60;
                if (rem == 0) {
                    minutes += 1;
                }
                String format = String.format("%02d", startTime % 60);
                label.setText(String.valueOf(minutes) + ":" + format);
            }
        });

        timer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (startTime == 299) {
                    timer.stop();
                }
            }
        });

        timer.start();
    }

    /*pause time */
    public void pauseTimer() {
        timer.stop();
    }
    /*resume time */
    public void resumeTimer() {
        timer.start();
    }

}
