package dc1_2;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class DialogClockSettingFontSize extends Dialog {

    private PanelInputNumber inputFontSize;
    private DialogClickOkClickListener mListener;
    private MyClockFrame frame;

    public DialogClockSettingFontSize (Frame owner, String title) {
        super(owner, title, true);
        if (owner instanceof MyClockFrame) {
            frame = (MyClockFrame) owner;
        }
        Button buttonOk = new Button("OK");
        buttonOk.addActionListener((e) -> {
            mListener.onClick(this);
            setVisible(false);
        });
        add(buttonOk, BorderLayout.SOUTH);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                setVisible(false);
            }
        });

        init();
    }

    private void init() {
        Panel settingColorMain = new Panel();
        settingColorMain.setLayout(new GridLayout(1, 1));
        inputFontSize = new PanelInputNumber("Font size", Property.MAX_FONT_SIZE, (int) frame.getProperty().getTimeFontSize());
        inputFontSize.setTextFieldSize(3);
        settingColorMain.add(inputFontSize);
        add(settingColorMain, BorderLayout.CENTER);
    }

    public void setOnOkClickListener (DialogClickOkClickListener listener) {
        mListener = listener;
    }

    public int getFontSize () {
        return inputFontSize.getInputNumber();
    }

    public static interface DialogClickOkClickListener {
        public void onClick(DialogClockSettingFontSize dialog);
    }

}
