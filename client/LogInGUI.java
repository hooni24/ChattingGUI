package client;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.Color;

class LogInGUI extends JFrame implements ActionListener{		//1��. GUI ��ü�� Listener Ŭ������ �Ǵ� ���.
	private JLabel lbl_Title, lbl_id, lbl_pw ;
	private JTextField tf_id; 
	private JPasswordField tf_pw;
	private JButton btn_ok, btn_cancel;
	
	public LogInGUI(){
		
		setTitle("LogIn");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		
		getContentPane().setLayout(new BorderLayout());
		
		lbl_Title = new JLabel("  Chatting Login  ", JLabel.CENTER);		//CENTER�� �ϸ� �߾�����
		Font font = new Font("Tahoma", Font.BOLD, 80);
		lbl_Title.setFont(new Font("Tahoma", Font.BOLD, 20));
		getContentPane().add(lbl_Title, BorderLayout.NORTH);
		
		
		lbl_id = new JLabel("ID");
		lbl_id.setPreferredSize(new Dimension(18, 15));
		lbl_id.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tf_id = new JTextField(5);
		JPanel pnl_center_id = new JPanel();
		pnl_center_id.add(lbl_id);	pnl_center_id.add(tf_id);

		
		lbl_pw = new JLabel("PW");
		lbl_pw.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tf_pw = new JPasswordField(5);
		tf_pw.setDisabledTextColor(Color.LIGHT_GRAY);
		tf_pw.setEnabled(false);
		JPanel pnl_center_pw = new JPanel();
		pnl_center_pw.add(lbl_pw);	pnl_center_pw.add(tf_pw);
		
		
		JPanel pnl_center = new JPanel();
		pnl_center.setLayout(new GridLayout(2, 1, 10, 5));
		pnl_center.add(pnl_center_id);	pnl_center.add(pnl_center_pw);
		getContentPane().add(pnl_center, BorderLayout.CENTER);
		
		
		btn_ok = new JButton("Ok");
		btn_cancel = new JButton("Cancel");
		
/**/	btn_ok.addActionListener(this);			//�� LogInGUI ��ü�� listenerŬ������ ��ü�� �Ǳ� ������ this�� �ڱ� �ڽ��� �ִ´�(�ڽ��� ��ü)
		btn_cancel.addActionListener(this);		//button���� action�̺�Ʈ�� �ϰ� �����ϱ� actionListener
	
												//�� �������� ok, cancel ��ư�� ������ actionListener�������̽��� �������̵��� actionPerformed() �޼ҵ尡 ����ȴ�.
		
		
		JPanel pnl_south = new JPanel();
		pnl_south.add(btn_ok);	pnl_south.add(btn_cancel);
		getContentPane().add(pnl_south, BorderLayout.SOUTH);
		
		pack();
		setVisible(true);
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {	//��ư 2�� ��ο� ���� �̰� �۵��ϱ� ������ �� �޼ҵ� �ȿ��� �� ���̸� ����������� �Ѵ�.
													//�� �޼ҵ尡 ����� �� ActionEvent��ü�� e�� ���������.
		Object source = e.getSource();				//getSource()�� �̺�Ʈ�� �߻��� ������Ʈ ��ü�� ��ȯ�Ѵ�. (Object��)
		if (source == btn_ok){						//���� ��ư�� ������ e�� ��������µ� �갡 btn_ok���..
			String id = tf_id.getText();			//tf_id�� �Էµ� ���ڿ��� �޾ƿͼ� String id�� �ִ´�.
			if(id == null || id.length() < 5){
				JOptionPane.showMessageDialog(this, "ID�� 5�� �̻� �Է��ؾ� �մϴ�.", "ID�Է½���", JOptionPane.PLAIN_MESSAGE);
			}else {
				dispose();
				new ChattingRoom(id);
			}//id�˻� if��
			
		}else if(source == btn_cancel){				//���⼭�� cancel Ŭ���� �ԷµǾ��ִ� ���� ����� ����� ���Ѵ�.
			tf_id.setText("");
			tf_pw.setText("");
			tf_id.requestFocus();					//tf_id�� focus�� �����δ�.
		}//ok���� cancel���� Ȯ���ϴ� if��
		
	}//actionPerformed()
	
	
}//class
