package client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class ChattingRoom extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField tf_input;
	private JScrollPane sp_chatMessage;
	private JTextArea ta_chatMessage;
	private JPanel p_south;
	private JButton btn_send;
	private JPanel p_east;
	private JLabel lbl_uestList;
	private JScrollPane sp_userList;
	private JLabel lbl_totalUser;
	
	private String id;
	private JMenuBar menuBar;
	private JMenu me_file;
	private JMenu me_help;
	private JMenuItem mi_save;
	private JMenuItem mi_help;
	private JSeparator separator;
	private JMenuItem mi_exit;
	private JTextArea ta_uesrList;
	private ChattingClientManager clientMgr;

/*	*//**
	 * Launch the application.
	 *//*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChattingRoom frame = new ChattingRoom();				//���̻� main�޼ҵ�� �ʿ����. LogInGUI���� �Ѿ�ͼ� �� GUI�� ����� ���̱� ����.
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
*/

	
	
	
	/**
	 * Create the frame.
	 * @param id �Է�
	 */
	public ChattingRoom(String id) {
		clientMgr = new ChattingClientManager();
		this.id = id;
		
		setTitle("�Ĵ� ä�� ���α׷�");
		setBounds(100, 100, 672, 474);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		me_file = new JMenu("File");
		me_file.setMnemonic('F');
		me_file.setMnemonic(KeyEvent.VK_F);
		me_file.setName("");
		menuBar.add(me_file);
		
		mi_save = new JMenuItem("\uB300\uD654\uB0B4\uC6A9 \uC800\uC7A5");
		mi_save.addActionListener(this);
		mi_save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		me_file.add(mi_save);
		
		separator = new JSeparator();
		me_file.add(separator);
		
		mi_exit = new JMenuItem("\uC885\uB8CC");
		mi_exit.addActionListener(this);
		mi_exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_MASK));
		me_file.add(mi_exit);
		
		me_help = new JMenu("Help");
		me_help.setMnemonic('H');
		me_help.setMnemonic(KeyEvent.VK_H);
		menuBar.add(me_help);
		
		mi_help = new JMenuItem("\uD504\uB85C\uADF8\uB7A8 \uC815\uBCF4");
		mi_help.addActionListener(this);
		me_help.add(mi_help);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(5, 5));
		setContentPane(contentPane);
		
		
		addWindowListener(new WindowAdapter() {					//�͸�Ŭ������ ����ؼ�  listener��ü�� �����ߴ�. (WindowListener �������̽��� �ִ� �޼ҵ带 ������ �����Ŭ���� ��)
			@Override
			public void windowOpened(WindowEvent e) {
				tf_input.requestFocus();						//�����찡 ��������, tf_input�� ��Ŀ���� �����.
			}

			@Override
			public void windowClosing(WindowEvent e) {			//�������� X��ư�� ������ �� ��Ÿ���� �޼ҵ� 
				closeOperation();
			}
			
			
		});
		
		
		sp_chatMessage = new JScrollPane();
		contentPane.add(sp_chatMessage, BorderLayout.CENTER);
		
		ta_chatMessage = new JTextArea();
		ta_chatMessage.setDisabledTextColor(Color.BLACK);
		ta_chatMessage.setEnabled(false);
		ta_chatMessage.setTabSize(10);
		ta_chatMessage.setRows(20);
		sp_chatMessage.setViewportView(ta_chatMessage);
		
		p_south = new JPanel();
		contentPane.add(p_south, BorderLayout.SOUTH);
		p_south.setLayout(new BorderLayout(0, 0));
		
		tf_input = new JTextField();
		tf_input.addActionListener(this);
		p_south.add(tf_input, BorderLayout.CENTER);
		tf_input.setColumns(10);
		
		btn_send = new JButton("\uC804\uC1A1");
		btn_send.addActionListener(this);
		p_south.add(btn_send, BorderLayout.EAST);
		
		p_east = new JPanel();
		p_east.setPreferredSize(new Dimension(150, 10));
		contentPane.add(p_east, BorderLayout.EAST);
		p_east.setLayout(new BorderLayout(0, 0));
		
		lbl_uestList = new JLabel("\uC811\uC18D\uC790 \uBAA9\uB85D");
		lbl_uestList.setFont(new Font("����", Font.PLAIN, 12));
		lbl_uestList.setHorizontalAlignment(SwingConstants.CENTER);
		p_east.add(lbl_uestList, BorderLayout.NORTH);
		
		sp_userList = new JScrollPane();
		p_east.add(sp_userList, BorderLayout.CENTER);
		
		ta_uesrList = new JTextArea();
		ta_uesrList.setEnabled(false);
		sp_userList.setViewportView(ta_uesrList);
		
		lbl_totalUser = new JLabel("\uCD1D 00\uBA85");
		lbl_totalUser.setHorizontalAlignment(SwingConstants.CENTER);
		p_east.add(lbl_totalUser, BorderLayout.SOUTH);
		
		
		setVisible(true);
	}	
	
	
	private void closeOperation(){
		int option = JOptionPane.showConfirmDialog(this, "����??", "���α׷� ����", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
		if(option == JOptionPane.OK_OPTION){
			System.exit(0);
		}												//ĵ���� ���ؼ��� ���� ó�� ���ص� â�� ���� ������� �Ǿ� �ִ�.
	}
	
	
	

	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if(source == tf_input || source == btn_send){			//text field���� enter�� ������ �׼��� �߻��ߴٴ� ��.
			String message = String.format("[%s] %s%n", id, tf_input.getText());
			tf_input.setText("");
			ta_chatMessage.append(message);
			clientMgr.sendMessage(message);
			
			
		}else if (source == mi_save){
			JFileChooser fc = new JFileChooser();
			fc.showSaveDialog(this);
			File file = fc.getSelectedFile();
			String message = ta_chatMessage.getText();
			
			BufferedWriter bw;
			try {
				bw = new BufferedWriter(new FileWriter(file));
				bw.write(message);
				bw.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			
		}else if (source == mi_help){
			JOptionPane.showMessageDialog(this, "���� ��� : �輺��", "���α׷� ����", JOptionPane.PLAIN_MESSAGE);
		}else if (source == mi_exit){
			closeOperation();
		}
		
		
		
		
	}//actionPerformed()
	
	
	
	
	
	
	
	
}//class
