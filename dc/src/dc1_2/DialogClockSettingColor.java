package dc1_2;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class DialogClockSettingColor extends Dialog {

    public static final String TITLE_TIME_COLOR = "Time color";
    public static final String TITLE_BACKGROUND_COLOR = "Background color";

    private PanelInputNumber inputNumberRedValue;
    private PanelInputNumber inputNumberGreenValue;
    private PanelInputNumber inputNumberBlueValue;
    private DialogClickOkClickListener mListener;
    private String title;
    private MyClockFrame frame;

    public DialogClockSettingColor(Frame owner, String title) {
        super(owner, title, true);
        if (owner instanceof MyClockFrame) {
            frame = (MyClockFrame) owner;
        }
        this.title = title;
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
        settingColorMain.setLayout(new GridLayout(3, 1));
        int defautRedValue = 0;
        int defautGreenValue = 0;
        int defautBlueValue = 0;
        if (title.equals(TITLE_TIME_COLOR)) {
            defautRedValue = frame.getProperty().getTimeColor().getRed();
            defautGreenValue = frame.getProperty().getTimeColor().getGreen();
            defautBlueValue = frame.getProperty().getTimeColor().getBlue();
        } else if (title.equals(TITLE_BACKGROUND_COLOR)) {
            defautRedValue = frame.getProperty().getBackgroundColor().getRed();
            defautGreenValue = frame.getProperty().getBackgroundColor().getGreen();
            defautBlueValue = frame.getProperty().getBackgroundColor().getBlue();
        }
        inputNumberRedValue = new PanelInputNumber("R", Property.MAX_RGB_VALUE, defautRedValue);
        inputNumberRedValue.setTextFieldSize(3);
        inputNumberGreenValue = new PanelInputNumber("G", Property.MAX_RGB_VALUE, defautGreenValue);
        inputNumberGreenValue.setTextFieldSize(3);
        inputNumberBlueValue = new PanelInputNumber("B", Property.MAX_RGB_VALUE, defautBlueValue);
        inputNumberBlueValue.setTextFieldSize(3);
        settingColorMain.add(inputNumberRedValue);
        settingColorMain.add(inputNumberGreenValue);
        settingColorMain.add(inputNumberBlueValue);
        add(settingColorMain, BorderLayout.CENTER);
    }

    public void setOnOkClickListener (DialogClickOkClickListener listener) {
        mListener = listener;
    }

    public int getRedValue () {
        return inputNumberRedValue.getInputNumber();
    }

    public int getGreenValue () {
        return inputNumberGreenValue.getInputNumber();
    }

    public int getBlueValue () {
        return inputNumberBlueValue.getInputNumber();
    }

    public static interface DialogClickOkClickListener {
        public void onClick(DialogClockSettingColor dialog);
    }

}
