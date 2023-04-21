package day0412;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JComboBox;

// 수정할 학생의 이름,국어,영어,수학을 입력받아 
// 데이터베이스에 수정하는 프로그램을 GUI로 만들어 봅니다.
// 화면구성은 자유롭게 합니다.

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class StudentUpdateGUI_Combo extends JFrame {
	JComboBox<String> jcb_name;
	JTextField jtf_kor;
	JTextField jtf_eng;
	JTextField jtf_math;
	
	public StudentUpdateGUI_Combo() {
		jcb_name = new JComboBox<String>();
		jtf_kor = new JTextField(10);
		jtf_eng = new JTextField(10);
		jtf_math = new JTextField(10);
		
		loadName();
		
		setLayout(new FlowLayout());
		add(new JLabel("이름"));
		add(jcb_name);
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
		
		jcb_name.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String name = (String)jcb_name.getSelectedItem();
				System.out.println(name);
				loadScore(name);
			}
		});
		
		
		btn_update.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String name = (String)jcb_name.getSelectedItem();
				int kor = Integer.parseInt(jtf_kor.getText());
				int eng = Integer.parseInt(jtf_eng.getText());
				int math = Integer.parseInt(jtf_math.getText());
				
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
	
	// 데이터베이스 연결하여 모든 학생의 이름을 조회하여 
	// 콤보박스에 추가하는 메소드 정의
	public void loadName() {
		String sql = "select name from student";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = 
			DriverManager.getConnection(
				"jdbc:oracle:thin:@localhost:1521:XE", 
				"c##sist", 
				"sist");
			Statement stmt = 
					conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				String name = rs.getString(1);
				jcb_name.addItem(name);
			}
			rs.close();
			stmt.close();
			conn.close();
		}catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		}
	}
	
	//이름을 매개변수로 전달받아 
	//해당 학생의 국어,영어,수학을 조회하여 
	//각각의 텍스트필드에 출력하는 메소드
	public void loadScore(String name) {
		String sql = "select kor,eng,math from student where name = '"+name+"'";
		System.out.println(sql);
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn 
			= DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:XE", 
					"c##sist", 
					"sist");
			Statement stmt = 
					conn.createStatement();
			ResultSet rs = 
				stmt.executeQuery(sql);
			if(rs.next()) {
				int kor = rs.getInt(1);
				int eng = rs.getInt(2);
				int math = rs.getInt(3);
				jtf_kor.setText(kor+"");
				jtf_eng.setText(eng+"");
				jtf_math.setText(math+"");
			}
			
			rs.close();
			stmt.close();
			conn.close();
		}catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		}
	}
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new StudentUpdateGUI_Combo();
	}
}





