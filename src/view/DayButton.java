package view;

import javax.swing.*;

public class DayButton  extends JButton {
    private int index;

    public DayButton(int index){
        this.index = index++;
        this.setText(Integer.toString(index));
    }
}
