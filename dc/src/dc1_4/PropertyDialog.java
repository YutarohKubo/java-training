package dc1_4;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class PropertyDialog extends Dialog {

    private GridBagLayout dialogLayout = new GridBagLayout();
    private Choice fontChoice;
    private Choice fontSizeChoice;
    private Choice timeColorChoice;
    private Choice backgroundColorChoice;
    private MyClockFrame frame;
    private DialogClickOkClickListener mListener;

    public PropertyDialog (Window owner, String title) {
        super(new Frame(), title, true);
        if (owner instanceof MyClockFrame) {
            frame = (MyClockFrame) owner;
        }
        setAlwaysOnTop(true);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                setVisible(false);
            }
        });

        init();
    }

    private void init() {
        setLayout(dialogLayout);
        Label labelFont = new Label("Font: ", Label.RIGHT);
        Label labelFontSize = new Label("Font size: ", Label.RIGHT);
        Label labelTimeColor = new Label("Time color: ", Label.RIGHT);
        Label labelBackgroundColor = new Label("Background color: ", Label.RIGHT);
        makeFontChoice();
        makeFontSizeChoice();
        makeTimeColorChoice();
        makeBackgroundColorChoice();
        Button buttonOk = new Button("  OK  ");
        buttonOk.addActionListener((e) -> {
            mListener.onClick(this);
            setVisible(false);
        });
        Button buttonCancel = new Button("Cancel");
        buttonCancel.addActionListener(e -> {
            setVisible(false);
        });
        addComponent(labelFont, 0, 0, 1, 1, GridBagConstraints.EAST);
        addComponent(labelFontSize, 0, 1, 1, 1, GridBagConstraints.EAST);
        addComponent(labelTimeColor, 0, 2, 1, 1, GridBagConstraints.EAST);
        addComponent(labelBackgroundColor, 0, 3, 1, 1, GridBagConstraints.EAST);
        addComponent(new Label(""), 0, 4, 1, 1, GridBagConstraints.EAST);
        addComponent(fontChoice, 1, 0, 1, 1, GridBagConstraints.WEST);
        addComponent(fontSizeChoice, 1, 1, 1, 1, GridBagConstraints.WEST);
        addComponent(timeColorChoice, 1, 2, 1, 1, GridBagConstraints.WEST);
        addComponent(backgroundColorChoice, 1, 3, 1, 1, GridBagConstraints.WEST);
        addComponent(new Label(""), 1, 4, 1, 1, GridBagConstraints.WEST);
        addComponent(buttonOk, 2, 5, 1, 1, GridBagConstraints.CENTER);
        addComponent(buttonCancel, 3, 5, 1, 1, GridBagConstraints.CENTER);
    }

    private void makeFontChoice () {
        fontChoice = new Choice();
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        for (String item : ge.getAvailableFontFamilyNames()) {
            fontChoice.add(item);
        }
        fontChoice.select(frame.getProperty().getTimeFont().getName());
    }

    private void makeFontSizeChoice () {
        fontSizeChoice = new Choice();
        Property.AppFontSize[] fontSizes = Property.getAppFontSize();
        for (Property.AppFontSize item : fontSizes) {
            fontSizeChoice.add(item.getName());
        }
        fontSizeChoice.select(frame.getProperty().getTimeFontSizeName());
    }

    private void makeTimeColorChoice () {
        timeColorChoice = new Choice();
        Property.AppColor[] colors = Property.getAppColors();
        for (Property.AppColor color : colors) {
            timeColorChoice.add(color.getName());
        }
        timeColorChoice.select(frame.getProperty().getTimeColorName());
    }

    private void makeBackgroundColorChoice () {
        backgroundColorChoice = new Choice();
        Property.AppColor[] colors = Property.getAppColors();
        for (Property.AppColor color : colors) {
            backgroundColorChoice.add(color.getName());
        }
        backgroundColorChoice.select(frame.getProperty().getBackgroundColorName());
    }

    public String getChoosedFont () {
        return fontChoice.getSelectedItem();
    }

    public String getChoosedFontSize () {
        return fontSizeChoice.getSelectedItem();
    }

    public String getChoosedTimeColor () {
        return timeColorChoice.getSelectedItem();
    }

    public String getChoosedBackgroundColor () {
        return backgroundColorChoice.getSelectedItem();
    }

    private void addComponent (Component component, int x, int y, int w, int h) {
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.gridx = x;
        gridBagConstraints.gridy = y;
        gridBagConstraints.gridwidth = w;
        gridBagConstraints.gridheight = h;
        dialogLayout.setConstraints(component, gridBagConstraints);
        add(component);
    }

    private void addComponent (Component component, int x, int y, int w, int h, int align) {
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.gridx = x;
        gridBagConstraints.gridy = y;
        gridBagConstraints.gridwidth = w;
        gridBagConstraints.gridheight = h;
        gridBagConstraints.anchor = align;
        dialogLayout.setConstraints(component, gridBagConstraints);
        add(component);
    }

    public void setOnOkClickListener (DialogClickOkClickListener listener) {
        mListener = listener;
    }

    public static interface DialogClickOkClickListener {
        public void onClick(PropertyDialog dialog);
    }

}
