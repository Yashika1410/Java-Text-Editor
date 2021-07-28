import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;

import java.awt.CardLayout;
import javax.swing.JTextArea;
import javax.swing.SpringLayout;
import javax.swing.undo.UndoManager;

import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.awt.event.ActionEvent;
import javax.swing.BoxLayout;
import java.awt.BorderLayout;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

public class text_editor {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					text_editor window = new text_editor();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public text_editor() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 505, 490);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JTextArea textArea = new JTextArea();
		frame.getContentPane().add(textArea, BorderLayout.CENTER);
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		UndoManager undo = new UndoManager(); 
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmNew = new JMenuItem("New");
		mntmNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textArea.setText("");
			}
		});
		mnFile.add(mntmNew);
		
		JMenuItem mntmSave = new JMenuItem("Save");
		mntmSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser filechooser = new JFileChooser("f:");
	            int temp = filechooser.showSaveDialog(null);
	  
	            if (temp == JFileChooser.APPROVE_OPTION) {
	                File file = new File(filechooser.getSelectedFile().getAbsolutePath());
	  
	                try {
	                    FileWriter filewriter = new FileWriter(file, false);
	                    BufferedWriter bufferwr = new BufferedWriter(filewriter);
	                    bufferwr.write(textArea.getText());
	                    bufferwr.flush();
	                    bufferwr.close();
	                }
	                catch (Exception ex) {
	                    JOptionPane.showMessageDialog(frame, ex.getMessage());
	                }}
			}
		});
		mnFile.add(mntmSave);
		
		JMenuItem mntmOpen = new JMenuItem("Open");
		mntmOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser filechooser = new JFileChooser("f:");
	            int temp = filechooser.showOpenDialog(null);
	  
	            if (temp == JFileChooser.APPROVE_OPTION) {
	                File file = new File(filechooser.getSelectedFile().getAbsolutePath());
	  
	                try {
	                	String str="",str1="";
	                	FileReader fileread = new FileReader(file);
	                	BufferedReader bufferrd = new BufferedReader(fileread);
	                	str1=bufferrd.readLine();
	                	while((str=bufferrd.readLine())!=null) {
	                		str1=str1+"\n"+str;
	                	}
	                	textArea.setText(str1);
	                }
	                catch(Exception ex) {
	                	JOptionPane.showMessageDialog(frame, ex.getMessage());
	                }
	                }
	            }	                
			
		});
		mnFile.add(mntmOpen);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mnFile.add(mntmExit);
		
		JMenu mnEdit = new JMenu("Edit");
		menuBar.add(mnEdit);
		
		JMenuItem mntmCut = new JMenuItem("Cut");
		mntmCut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.cut();
			}
		});
		mnEdit.add(mntmCut);
		
		JMenuItem mntmCopy = new JMenuItem("Copy");
		mntmCopy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.copy();
			}
		});
		mnEdit.add(mntmCopy);
		
		JMenuItem mntmExi = new JMenuItem("Paste");
		mntmExi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.paste();
			}
		});
		mnEdit.add(mntmExi);
		
		JMenuItem mntmUndo = new JMenuItem("Undo");
		mntmUndo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				undo.undo();
			}
		});
		mnEdit.add(mntmUndo);
		
		JMenuItem mntmRedo = new JMenuItem("Redo");
		mntmRedo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				undo.redo();
			}
		});
		mnEdit.add(mntmRedo);
		
		JMenu mnInfo = new JMenu("Info");
		menuBar.add(mnInfo);
		
		JLabel lblThisEditorIs = new JLabel("This Editor is designed in Java Swing");
		mnInfo.add(lblThisEditorIs);
		
		JSeparator separator = new JSeparator();
		mnInfo.add(separator);
		
		JLabel lblDevelopedByYashika = new JLabel("& Developed By Yashika Jain");
		mnInfo.add(lblDevelopedByYashika);		
		 JScrollPane scrollableTextArea = new JScrollPane(textArea);  
		 scrollableTextArea.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);  
	        scrollableTextArea.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);  
	        frame.getContentPane().add(scrollableTextArea);  
		
		
		
	}
}
