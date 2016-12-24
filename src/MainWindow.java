import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.SWT;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

public class MainWindow {

	protected Shell shell;
	private final FormToolkit formToolkit = new FormToolkit(Display.getDefault());
	private Text text;
	private Text text_1;
	private RomajaConverter romajaConverter = new RomajaConverter();

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			MainWindow window = new MainWindow();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
	
	public void convertRomaja(){
		String input = text.getText();
		String convertedText = romajaConverter.convert(input);
		text_1.setText(convertedText);
		copyConvertedToClipboard();
	}

	public void copyConvertedToClipboard(){
		String str = text_1.getText();
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Clipboard clipboard = toolkit.getSystemClipboard();
		StringSelection strSel = new StringSelection(str);
		clipboard.setContents(strSel, null);
	}
	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(450, 300);
		shell.setText("Romaja Converter");
		
		Button btnTransliterate = formToolkit.createButton(shell, "Transliterate", SWT.NONE);
		btnTransliterate.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				convertRomaja();
			}
		});
		btnTransliterate.setBounds(181, 72, 75, 25);
		
		text = new Text(shell, SWT.BORDER);
		text.setBounds(10, 20, 414, 25);
		formToolkit.adapt(text, true, true);
		
		text.addKeyListener(new KeyListener(){
			public void keyPressed(KeyEvent e){
				if(text.getText().length() < 1){
					return;
				}
				if(e.keyCode == SWT.CR)
					convertRomaja();
			}
			
			public void keyReleased(KeyEvent e){
				
			}
		});
		
		text_1 = new Text(shell, SWT.BORDER);
		text_1.setBounds(10, 148, 414, 25);
		formToolkit.adapt(text_1, true, true);
		
		Button btnCopyToClipboard = new Button(shell, SWT.NONE);
		btnCopyToClipboard.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				copyConvertedToClipboard();
			}
		});
		btnCopyToClipboard.setBounds(164, 189, 109, 25);
		formToolkit.adapt(btnCopyToClipboard, true, true);
		btnCopyToClipboard.setText("Copy to Clipboard");

	}
}
