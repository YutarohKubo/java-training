package dc1_2;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class DialogClockSettingFontName extends Dialog {

    private PanelChoice choiceFont;
    private DialogClickOkClickListener mListener;
    private MyClockFrame frame;

    public DialogClockSettingFontName(Frame owner, String title) {
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
        Panel settingFontMain = new Panel();
        settingFontMain.setLayout(new GridLayout(1, 1));
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        choiceFont = new PanelChoice("Fonts", ge.getAvailableFontFamilyNames(), frame.getProperty().getTimeFont().getName());
        settingFontMain.add(choiceFont);
        add(settingFontMain, BorderLayout.CENTER);
    }

    public String getSelectedFont () {
        return choiceFont.getSelectedItem();
    }

    public void setOnOkClickListener (DialogClickOkClickListener listener) {
        mListener = listener;
    }

    public static interface DialogClickOkClickListener {
        public void onClick(DialogClockSettingFontName dialog);
    }
}
