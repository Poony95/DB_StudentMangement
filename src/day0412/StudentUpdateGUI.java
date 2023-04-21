package day0412;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.swing.JButton;

// 수정할 학생의 이름,국어,영어,수학을 입력받아 
// 데이터베이스에 수정하는 프로그램을 GUI로 만들어 봅니다.
// 화면구성은 자유롭게 합니다.

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class StudentUpdateGUI extends JFrame {
	JTextField jtf_name;
	JTextField jtf_kor;
	JTextField jtf_eng;
	JTextField jtf_math;
	
	public StudentUpdateGUI() {
		jtf_name = new JTextField(10);
		jtf_kor = new JTextField(10);
		jtf_eng = new JTextField(10);
		jtf_math = new JTextField(10);
		
		setLayout(new FlowLayout());
		add(new JLabel("이름"));
		add(jtf_name);
		add(new JLabel("국어"));
		add(jtf_kor);
		add(new JLabel("영어"));
		add(jtf_eng);
		add(new JLabel("수학"));
		add(jtf_math);
		
		JButton btn_update = new JButton("수정");
		add(btn_update);
		
		setSize(800, 200);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		btn_update.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String name = jtf_name.getText();
				int kor = Integer.parseInt(jtf_kor.getText());
				int eng = Integer.parseInt(jtf_kor.getText());
				int math = Integer.parseInt(jtf_kor.getText());
				
				String sql = "update student set kor="+kor+",eng="+eng+",math="+math+" where name='"+name+"'";
				
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
					String url = "jdbc:oracle:thin:@localhost:1521:XE";
					String user = "c##sist";
					String pwd = "sist";
					Connection conn = 
					DriverManager.getConnection(url, user, pwd);
					Statement stmt = 
					conn.createStatement();
					int re = stmt.executeUpdate(sql);
					if(re > 0) {
						JOptionPane.showMessageDialog(null, "수정하였습니다.");
					}else {
						JOptionPane.showMessageDialog(null, "수정에 실패하였습니다.");
					}
					
					stmt.close();
					conn.close();
				}catch (Exception ex) {
					System.out.println("예외발생:"+ex.getMessage());
				}				
			}
		});		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new StudentUpdateGUI();
	}
}
