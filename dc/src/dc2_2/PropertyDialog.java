package dc2_2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class PropertyDialog extends JDialog {

    private GridBagLayout dialogLayout = new GridBagLayout();
    private JComboBox<String>  fontCombo;
    private DefaultComboBoxModel<String> fontComboModel;
    private JComboBox<String>  fontSizeCombo;
    private DefaultComboBoxModel<String> fontSizeComboModel;
    private JComboBox<String>  timeColorCombo;
    private DefaultComboBoxModel<String> timeColorComboModel;
    private JComboBox<String>  backgroundColorCombo;
    private DefaultComboBoxModel<String> backgroundColorModel;
    private ColorChipPanel timeColorChip;
    private ColorChipPanel backgroundColorChip;
    private MyClockFrame frame;
    private DialogClickOkClickListener mListener;

    public PropertyDialog(Window owner, String title) {
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
        JLabel labelFont = new JLabel("Font: ", JLabel.RIGHT);
        JLabel labelFontSize = new JLabel("Font size: ", JLabel.RIGHT);
        JLabel labelTimeColor = new JLabel("Time color: ", JLabel.RIGHT);
        JLabel labelBackgroundColor = new JLabel("Background color: ", JLabel.RIGHT);
        makeFontChoice();
        makeFontSizeChoice();
        makeTimeColorChoice();
        makeBackgroundColorChoice();
        JButton buttonOk = new JButton("  OK  ");
        buttonOk.addActionListener((e) -> {
            mListener.onClick(this);
            setVisible(false);
        });
        JButton buttonCancel = new JButton("Cancel");
        buttonCancel.addActionListener(e -> {
            setVisible(false);
        });
        addComponent(labelFont, 0, 0, 1, 1, GridBagConstraints.EAST);
        addComponent(labelFontSize, 0, 1, 1, 1, GridBagConstraints.EAST);
        addComponent(labelTimeColor, 0, 2, 1, 1, GridBagConstraints.EAST);
        addComponent(labelBackgroundColor, 0, 3, 1, 1, GridBagConstraints.EAST);
        addComponent(new JLabel(" "), 0, 4, 1, 1, GridBagConstraints.EAST);
        addComponent(fontCombo, 1, 0, 1, 1, GridBagConstraints.WEST);
        addComponent(fontSizeCombo, 1, 1, 1, 1, GridBagConstraints.WEST);
        addComponent(timeColorCombo, 1, 2, 1, 1, GridBagConstraints.WEST);
        addComponent(backgroundColorCombo, 1, 3, 1, 1, GridBagConstraints.WEST);
        addComponent(new JLabel(" "), 1, 4, 1, 1, GridBagConstraints.WEST);
        addComponent(timeColorChip, 2, 2, 1, 1, GridBagConstraints.CENTER);
        addComponent(backgroundColorChip, 2, 3, 1, 1, GridBagConstraints.CENTER);
        addComponent(buttonOk, 2, 5, 1, 1, GridBagConstraints.CENTER);
        addComponent(buttonCancel, 3, 5, 1, 1, GridBagConstraints.CENTER);
    }

    private void makeFontChoice () {
        fontComboModel = new DefaultComboBoxModel<>();
        fontCombo = new JComboBox<>(fontComboModel);
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        for (String item : ge.getAvailableFontFamilyNames()) {
            fontComboModel.addElement(item);
        }
        fontCombo.setSelectedItem(frame.getProperty().getTimeFont().getName());
    }

    private void makeFontSizeChoice () {
        fontSizeComboModel = new DefaultComboBoxModel<>();
        fontSizeCombo = new JComboBox<>(fontSizeComboModel);
        Property.AppFontSize[] fontSizes = Property.getAppFontSize();
        for (Property.AppFontSize item : fontSizes) {
            fontSizeComboModel.addElement(item.getName());
        }
        fontSizeCombo.setSelectedItem(frame.getProperty().getTimeFontSizeName());
    }

    private void makeTimeColorChoice () {
        timeColorComboModel = new DefaultComboBoxModel<>();
        timeColorCombo = new JComboBox<>(timeColorComboModel);
        Property.AppColor[] colors = Property.getAppColors();
        for (Property.AppColor color : colors) {
            timeColorComboModel.addElement(color.getName());
        }
        timeColorCombo.setSelectedItem(frame.getProperty().getTimeColorName());
        timeColorChip = new ColorChipPanel(frame.getProperty().getTimeColor());
        timeColorCombo.addActionListener((e) -> {
            timeColorChip.setBackgroundColor(getColorByColorName((String) timeColorComboModel.getSelectedItem()));
        });
    }

    private void makeBackgroundColorChoice () {
        backgroundColorModel = new DefaultComboBoxModel<>();
        backgroundColorCombo = new JComboBox<>(backgroundColorModel);
        Property.AppColor[] colors = Property.getAppColors();
        for (Property.AppColor color : colors) {
            backgroundColorModel.addElement(color.getName());
        }
        backgroundColorCombo.setSelectedItem(frame.getProperty().getBackgroundColorName());
        backgroundColorChip = new ColorChipPanel(frame.getProperty().getBackgroundColor());
        backgroundColorCombo.addActionListener((e) -> {
            backgroundColorChip.setBackgroundColor(getColorByColorName((String) backgroundColorModel.getSelectedItem()));
        });

    }

    private Color getColorByColorName(String colorName) {
        for (Property.AppColor appColor : Property.appColors) {
            if (colorName.equals(appColor.getName())) {
                return appColor.getColor();
            }
        }
        throw new IllegalArgumentException("Nothing such color");
    }

    public String getChoosedFont () {
        return (String) fontCombo.getSelectedItem();
    }

    public String getChoosedFontSize () {
        return (String) fontSizeCombo.getSelectedItem();
    }

    public String getChoosedTimeColor () {
        return (String) timeColorCombo.getSelectedItem();
    }

    public String getChoosedBackgroundColor () {
        return (String) backgroundColorCombo.getSelectedItem();
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
