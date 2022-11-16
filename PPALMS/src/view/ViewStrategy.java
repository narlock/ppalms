package view;

import javax.swing.JPanel;

public abstract class ViewStrategy extends JPanel {
	public abstract void setViewPanel();
	public abstract void setControllerActions();
	public abstract void showErrorDialog(String message);
}
