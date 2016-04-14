package server;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class OpenGUI extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JPanel p_title;
	private JPanel p_menu;
	private JLabel lbl_title;
	private JButton btn_open;
	private JButton btn_exit;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OpenGUI frame = new OpenGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public OpenGUI() {
		setTitle("Chatting Server Open");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 265, 304);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		p_title = new JPanel();
		contentPane.add(p_title, BorderLayout.NORTH);
		
		lbl_title = new JLabel("Server Opener");
		lbl_title.setFont(new Font("±¼¸²", Font.PLAIN, 31));
		p_title.add(lbl_title);
		
		p_menu = new JPanel();
		contentPane.add(p_menu, BorderLayout.CENTER);
		p_menu.setLayout(null);
		
		btn_open = new JButton("OPEN");
		btn_open.addActionListener(this);
		btn_open.setFont(new Font("±¼¸²", Font.BOLD, 40));
		btn_open.setBounds(24, 10, 192, 82);
		p_menu.add(btn_open);
		
		btn_exit = new JButton("EXIT");
		btn_exit.addActionListener(this);
		btn_exit.setFont(new Font("±¼¸²", Font.BOLD, 40));
		btn_exit.setBounds(24, 102, 192, 82);
		p_menu.add(btn_exit);
	}

	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if(source == btn_open){
			dispose();
			new ChattingRoomServer();
		}else if(source == btn_exit){
			System.exit(0);
		}
	}
}
