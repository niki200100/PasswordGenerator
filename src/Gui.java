import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Gui implements ActionListener{

	//---------------objects for gui class----------------------//
	private Control control;
	private JFrame frame;
	private JPanel panel;
	private JLabel lbHeader, lbLenght, lbLetters, lbUpperCases, lbLowerCases, lbNumbers, lbNumberCount, lbEasyToWrite, lbSymbol, lbSymbolCount;
	private JButton btnGenerate, btnCopy;
	private JTextField password, tfLength, tfLC, tfUC, tfNC, tfSC;
	private Font fHeader, fPassword, fCopy, fSettings, fGenerate, fInsert;
	private JCheckBox cbETW, cbL, cbN, cbs;
	
	private boolean etw=false, letters=false, numbers=false, symbols=false;
	//---------------objects for gui class----------------------//
	
	public Gui(Control control) {//multitier architecture

		this.control= control;	//multitier architecture
		
		//---------------creating fonts--------------------------//
		fHeader=	new Font(Font.SANS_SERIF, Font.BOLD, 50);
		fPassword= 	new Font(Font.SANS_SERIF, Font.ITALIC, 20);
		fCopy=		new Font(Font.SANS_SERIF, Font.BOLD, 20);
		fSettings=	new Font(Font.SANS_SERIF, Font.PLAIN, 20);
		fGenerate=	new Font(Font.SANS_SERIF, Font.BOLD, 30);
		fInsert=	new Font(Font.SANS_SERIF, Font.BOLD, 15);
		//---------------creating fonts--------------------------//
	}
	
	public void createGui(){	//creates the gui
		
		//---------------frame and panel-------------------------//
		frame= new JFrame("PasswordGenerator");
		frame.setSize(1000,600);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel= new JPanel();
		panel.setLayout(null);
		panel.setVisible(true);
		panel.setOpaque(false);
		frame.add(panel);
		//---------------frame and panel-------------------------//
		
		//---------------everything else-------------------------//
		lbHeader= new JLabel("Password Generator");
		lbHeader.setBounds(200,50,600,50);
		lbHeader.setVisible(true);
		lbHeader.setFont(fHeader);
		lbHeader.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lbHeader);
		
		password= new JTextField();
		password.setBounds(100,150,800,50);
		password.setVisible(true);
		password.setFont(fPassword);
		password.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(password);
		
		btnCopy= new JButton("Copy");
		btnCopy.setBounds(800,205,99,40);
		btnCopy.setVisible(true);
		btnCopy.setFont(fCopy);
		btnCopy.setHorizontalAlignment(SwingConstants.CENTER);
		btnCopy.setBackground(Color.WHITE);
		btnCopy.setFocusable(false);
		btnCopy.addActionListener(this);
		panel.add(btnCopy);
		
		lbLenght= new JLabel("Length:");
		lbLenght.setBounds(320,250,80,50);
		lbLenght.setVisible(true);
		lbLenght.setFont(fSettings);
		panel.add(lbLenght);
		
		tfLength= new JTextField();
		tfLength.setBounds(400,263,50,25);
		tfLength.setVisible(true);
		tfLength.setFont(fInsert);
		tfLength.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(tfLength);	
		
		lbEasyToWrite= new JLabel("EasyToWrite:");
		lbEasyToWrite.setBounds(520,250,130,50);
		lbEasyToWrite.setVisible(true);
		lbEasyToWrite.setFont(fSettings);
		lbEasyToWrite.setToolTipText("Using alternately a letter or a number from the right or the left side of the keyboard");
		panel.add(lbEasyToWrite);
		
		cbETW= new JCheckBox();	
		cbETW.setBounds(650,260,30,30);
		cbETW.setVisible(true);
		cbETW.setBackground(Color.WHITE);
		cbETW.addItemListener(new ItemListener() {
		    @Override
		    public void itemStateChanged(ItemEvent e) {
		        if(e.getStateChange() == ItemEvent.SELECTED) {//checkbox has been selected
		            tfUC.setFocusable(false);
		            tfUC.setText("");
		            etw= true;
		            cbs.setEnabled(true);
		            cbs.setSelected(false);
		        } else {
		        	if(letters== true)
		        		tfUC.setFocusable(true);
		            etw=false;
		            cbs.setEnabled(false);
		            tfSC.setText("");
		            symbols= false;
		        };
		    }
		});
		panel.add(cbETW);
		
		btnGenerate= new JButton("Generate");
		btnGenerate.setBounds(400,500,200,50);
		btnGenerate.setVisible(true);
		btnGenerate.setFont(fGenerate);
		btnGenerate.setBackground(Color.WHITE);
		btnGenerate.setFocusable(false);
		btnGenerate.addActionListener(this);
		panel.add(btnGenerate);
		
		lbLetters= new JLabel("Letters:");
		lbLetters.setBounds(100,350,100,25);
		lbLetters.setVisible(true);
		lbLetters.setFont(fSettings);
		panel.add(lbLetters);
		
		cbL= new JCheckBox();	
		cbL.setBounds(170,348,30,30);
		cbL.setVisible(true);
		cbL.setBackground(Color.WHITE);
		cbL.addItemListener(new ItemListener() {
		    @Override
		    public void itemStateChanged(ItemEvent e) {
		        if(e.getStateChange() == ItemEvent.SELECTED) {//checkbox has been selected
		            letters= true;
		            tfLC.setFocusable(true);
		            if(etw==false)
		            	tfUC.setFocusable(true);
		        } else {
		            tfLC.setFocusable(false);
		            tfUC.setFocusable(false);
		            tfLC.setText("");
		            tfUC.setText("");
		            letters= false;
		        };
		    }
		});
		panel.add(cbL);
		
		lbLowerCases= new JLabel("LowerCases:");
		lbLowerCases.setBounds(100,390,120,25);
		lbLowerCases.setVisible(true);
		lbLowerCases.setFont(fSettings);
		panel.add(lbLowerCases);
		
		tfLC= new JTextField();
		tfLC.setBounds(230,390,40,25);
		tfLC.setVisible(true);
		tfLC.setFont(fInsert);
		tfLC.setHorizontalAlignment(SwingConstants.CENTER);
		tfLC.setFocusable(false);
		panel.add(tfLC);	
		
		lbUpperCases= new JLabel("UpperCases:");
		lbUpperCases.setBounds(100,430,120,25);
		lbUpperCases.setVisible(true);
		lbUpperCases.setFont(fSettings);
		panel.add(lbUpperCases);
		
		tfUC= new JTextField();
		tfUC.setBounds(230,430,40,25);
		tfUC.setVisible(true);
		tfUC.setFont(fInsert);
		tfUC.setHorizontalAlignment(SwingConstants.CENTER);
		tfUC.setFocusable(false);
		panel.add(tfUC);	
		
		lbNumbers= new JLabel("Numbers:");
		lbNumbers.setBounds(400,350,100,25);
		lbNumbers.setVisible(true);
		lbNumbers.setFont(fSettings);
		panel.add(lbNumbers);
		
		cbN= new JCheckBox();	
		cbN.setBounds(485,348,30,30);
		cbN.setVisible(true);
		cbN.setBackground(Color.WHITE);
		cbN.addItemListener(new ItemListener() {
		    @Override
		    public void itemStateChanged(ItemEvent e) {
		        if(e.getStateChange() == ItemEvent.SELECTED) {//checkbox has been selected
		            tfNC.setFocusable(true);
		            numbers= true;
		        } else {
		            tfNC.setFocusable(false);
		            tfNC.setText("");
		            numbers= false;
		        };
		    }
		});
		panel.add(cbN);
		
		lbNumberCount= new JLabel("Number Count:");
		lbNumberCount.setBounds(400,390,150,25);
		lbNumberCount.setVisible(true);
		lbNumberCount.setFont(fSettings);
		panel.add(lbNumberCount);
		
		tfNC= new JTextField();
		tfNC.setBounds(545,390,40,25);
		tfNC.setVisible(true);
		tfNC.setFont(fInsert);
		tfNC.setHorizontalAlignment(SwingConstants.CENTER);
		tfNC.setFocusable(false);
		panel.add(tfNC);	
		
		lbSymbol= new JLabel("Symbols:");
		lbSymbol.setBounds(700,350,100,25);
		lbSymbol.setVisible(true);
		lbSymbol.setFont(fSettings);
		panel.add(lbSymbol);
		
		cbs= new JCheckBox();	
		cbs.setBounds(785,348,30,30);
		cbs.setVisible(true);
		cbs.setBackground(Color.WHITE);
		cbs.setEnabled(false);
		cbs.addItemListener(new ItemListener() {
		    @Override
		    public void itemStateChanged(ItemEvent e) {
		        if(e.getStateChange() == ItemEvent.SELECTED) {//checkbox has been selected
		            symbols= true;
		            tfSC.setFocusable(true);
		        } else {
		        	symbols= false;
		            tfSC.setFocusable(false);
		            tfSC.setText("");
		        };
		    }
		});
		panel.add(cbs);
		
		lbSymbolCount= new JLabel("Symbol Count:");
		lbSymbolCount.setBounds(700,390,150,25);
		lbSymbolCount.setVisible(true);
		lbSymbolCount.setFont(fSettings);
		panel.add(lbSymbolCount);
		
		tfSC= new JTextField();
		tfSC.setBounds(845,390,40,25);
		tfSC.setVisible(true);
		tfSC.setFont(fInsert);
		tfSC.setHorizontalAlignment(SwingConstants.CENTER);
		tfSC.setFocusable(false);
		panel.add(tfSC);	
		
		panel.repaint();
		//---------------everything else-------------------------//
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()== btnGenerate){
			
			int length= Integer.parseInt(tfLength.getText());
			boolean uppercases; if(tfUC.isFocusable()==true)uppercases=true;else uppercases= false;
			int uCCount; if(!tfUC.getText().equals(""))uCCount=Integer.parseInt(tfUC.getText());else uCCount= 0;
			boolean lowercases; if(tfLC.isFocusable()==true)lowercases=true;else lowercases= false;
			int lCCount; if(!tfLC.getText().equals(""))lCCount=Integer.parseInt(tfLC.getText());else lCCount= 0;
			int ncount; if(!tfNC.getText().equals(""))ncount=Integer.parseInt(tfNC.getText());else ncount= 0;
			int sCount; if(!tfSC.getText().equals(""))sCount=Integer.parseInt(tfSC.getText());else sCount= 0;
			
			password.setText(control.getPassword(length, letters, uppercases, uCCount, lowercases, lCCount, numbers, ncount, etw, symbols, sCount));
		}
		
		if(e.getSource()== btnCopy){
			
			Toolkit toolkit = Toolkit.getDefaultToolkit();
			Clipboard clipboard = toolkit.getSystemClipboard();
			StringSelection strSel = new StringSelection(password.getText());
			clipboard.setContents(strSel, null);
		}
	}

}
