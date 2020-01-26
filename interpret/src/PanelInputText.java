import java.awt.FlowLayout;

import javax.swing.*;

public class PanelInputText extends JPanel {

	JLabel jLabel;
	JTextField textField;

	/*
	 * "名前" + 名前入力欄 のように
	 * テキストとテキストボックスがセットとなったクラス
	 */
	public PanelInputText (String label) {

		this.setLayout(new FlowLayout());
		this.setOpaque(false);

		jLabel = new JLabel(label);

		textField = new JTextField(20);

		this.add(jLabel);
		this.add(textField);

	}


	public JTextField getTextField() {
		return textField;
	}


	public void setText (String str) {
		textField.setText(str);
	}


	public String getText () {
		return textField.getText();
	}

	public void setTitle(String title){
		jLabel.setText(title);
	}

}
