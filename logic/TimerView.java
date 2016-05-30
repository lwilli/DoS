package logic;

import oracle.jrockit.jfr.JFR;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.Timer;

import javax.swing.JLabel;
import javax.swing.GroupLayout;
public class TimerView {
    private Timer timer;
    private int minutes;
    private int startTime;
    private int endTime;
    private JLabel label;
    private boolean finish = false;

    public TimerView (int start, JLabel label) {
        this.minutes = 0;
        this.startTime = start;
        this.endTime = 3000;
    }

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

    public void pauseTimer() {
        timer.stop();
    }

    public void resumeTimer() {
        timer.start();
    }
}
