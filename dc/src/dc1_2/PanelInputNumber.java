package dc1_2;

import java.awt.*;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;

public class PanelInputNumber extends Panel {

    private TextField mTextField;
    private int defaultNum;

    public PanelInputNumber (String title, int maxNum, int defaultNum) {
        this.setLayout(new FlowLayout());
        this.defaultNum = defaultNum;
        Label label = new Label(title);
        mTextField = new TextField();
        mTextField.setText(Integer.toString(defaultNum));
        mTextField.addTextListener(new TextListener() {
            @Override
            public void textValueChanged(TextEvent e) {
                String text = mTextField.getText();
                int textLength = mTextField.getText().length();
                if (textLength == 0) {
                    return;
                }
                for (int i = 0; i < textLength; i++) {
                    if (text.charAt(i) < 48 || text.charAt(i) > 57) {
                        mTextField.setText(text.substring(0, i) + text.substring(i + 1));
                        return;
                    }
                }
                String textInZero = mTextField.getText();
                if (textInZero.length() > 1 && textInZero.charAt(0) == 48) {
                    mTextField.setText(textInZero.substring(1));
                }
                String numText = mTextField.getText();
                System.out.println("numText = " + numText);
                try {
                    if (Integer.parseInt(numText) > maxNum) {
                        mTextField.setText(numText.substring(0, numText.length() - 1));
                    }
                } catch (NumberFormatException ex) {
                    ex.printStackTrace();
                }
            }
        });
        this.add(label);
        this.add(mTextField);
    }

    public void setTextFieldSize (int size) {
        mTextField.setColumns(size);
    }

    public int getInputNumber () {
        try{
            return Integer.parseInt(mTextField.getText());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return defaultNum;
        }
    }
}
