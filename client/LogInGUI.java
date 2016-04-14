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

class LogInGUI extends JFrame implements ActionListener{		//1번. GUI 자체가 Listener 클래스가 되는 방법.
	private JLabel lbl_Title, lbl_id, lbl_pw ;
	private JTextField tf_id; 
	private JPasswordField tf_pw;
	private JButton btn_ok, btn_cancel;
	
	public LogInGUI(){
		
		setTitle("LogIn");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		
		getContentPane().setLayout(new BorderLayout());
		
		lbl_Title = new JLabel("  Chatting Login  ", JLabel.CENTER);		//CENTER로 하면 중앙정렬
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
		
/**/	btn_ok.addActionListener(this);			//이 LogInGUI 자체가 listener클래스의 객체가 되기 때문에 this로 자기 자신을 넣는다(자신의 객체)
		btn_cancel.addActionListener(this);		//button으로 action이벤트를 하고 싶으니까 actionListener
	
												//즉 이제부터 ok, cancel 버튼을 누르면 actionListener인터페이스가 오버라이딩한 actionPerformed() 메소드가 실행된다.
		
		
		JPanel pnl_south = new JPanel();
		pnl_south.add(btn_ok);	pnl_south.add(btn_cancel);
		getContentPane().add(pnl_south, BorderLayout.SOUTH);
		
		pack();
		setVisible(true);
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {	//버튼 2개 모두에 대해 이게 작동하기 때문에 이 메소드 안에서 둘 사이를 구분지어줘야 한다.
													//이 메소드가 실행될 때 ActionEvent객체인 e가 만들어진다.
		Object source = e.getSource();				//getSource()는 이벤트가 발생된 컴포넌트 객체를 반환한다. (Object로)
		if (source == btn_ok){						//뭔가 버튼을 눌러서 e가 만들어졌는데 얘가 btn_ok라면..
			String id = tf_id.getText();			//tf_id에 입력된 문자열을 받아와서 String id에 넣는다.
			if(id == null || id.length() < 5){
				JOptionPane.showMessageDialog(this, "ID는 5자 이상 입력해야 합니다.", "ID입력실패", JOptionPane.PLAIN_MESSAGE);
			}else {
				dispose();
				new ChattingRoom(id);
			}//id검사 if문
			
		}else if(source == btn_cancel){				//여기서는 cancel 클릭시 입력되어있는 값을 지우는 방식을 취한다.
			tf_id.setText("");
			tf_pw.setText("");
			tf_id.requestFocus();					//tf_id로 focus를 움직인다.
		}//ok인지 cancel인지 확인하는 if문
		
	}//actionPerformed()
	
	
}//class
