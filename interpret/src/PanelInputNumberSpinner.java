import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

/**
 * "名前" + 数値入力欄 のように
 * テキストと数値選択ボックスがセットとなったクラス
 */
public class PanelInputNumberSpinner extends JPanel {

	JSpinner spinner;
	SpinnerNumberModel model;

	public PanelInputNumberSpinner (String label, int value, int min, int max, int step){
		this.setLayout(new FlowLayout());
		this.setOpaque(false);
		JLabel jLabel = new JLabel(label);
		model = new SpinnerNumberModel(value, min, max, step);
		spinner = new JSpinner(model);
		spinner.setPreferredSize(new Dimension(50, 25));
		JSpinner.DefaultEditor editor = new JSpinner.DefaultEditor(spinner);
		spinner.setEditor(editor);
		JFormattedTextField ftext = editor.getTextField();
		ftext.setEditable(false);
		this.add(jLabel);
		this.add(spinner);
	}

	public int getValue () {
		return (int) model.getValue();
	}

	public void setInitValue () {
		model.setValue(0);
	}

	public JSpinner getSpinner() {
		return spinner;
	}

}
