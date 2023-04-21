package exam01;
// 
//

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import db.ConnectionProvider;
public class StudentAllTable_TOTAVG extends JFrame {
	JTable table;
	Vector<Vector<String>> rowData;
	Vector<String> colNames;
	
	JTextField jtf_name,jtf_kor, jtf_eng, jtf_math;
	
	public StudentAllTable_TOTAVG() {
		jtf_name = new JTextField(10);
		jtf_kor = new JTextField(10);
		jtf_eng = new JTextField(10);
		jtf_math = new JTextField(10);
		
		colNames = new Vector<String>();
		colNames.add("이름");
		colNames.add("국어");
		colNames.add("영어");
		colNames.add("수학");
		colNames.add("총점");
		colNames.add("평균");
		
		rowData = new Vector<Vector<String>>();
		
		table = new JTable(rowData, colNames);
		JScrollPane jsp = new JScrollPane(table);
		add(jsp, BorderLayout.CENTER);
		
		JPanel p1 = new JPanel();
		p1.add(new JLabel("이름"));
		p1.add(jtf_name);
		p1.add(new JLabel("국어"));
		p1.add(jtf_kor);
		p1.add(new JLabel("영어"));
		p1.add(jtf_eng);
		p1.add(new JLabel("수학"));
		p1.add(jtf_math);
		
		JPanel p2 = new JPanel();
		JButton btn_add = new JButton("등록");
		JButton btn_update = new JButton("수정");
		JButton btn_delete = new JButton("삭제");
		p2.add(btn_add);
		p2.add(btn_update);
		p2.add(btn_delete);
		
		JPanel p_south = new JPanel();
		p_south.setLayout(new BorderLayout());
		p_south.add(p1, BorderLayout.CENTER);
		p_south.add(p2, BorderLayout.SOUTH);
		
		add(p_south, BorderLayout.SOUTH);		
		readStudent();		
		setSize(800, 300);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		table.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				int idx = table.getSelectedRow();
				Vector<String>  row = rowData.get(idx);
				jtf_name.setText(row.get(0));
				jtf_kor.setText(row.get(1)); 
				jtf_eng.setText(row.get(2));
				jtf_math.setText(row.get(3));
			}
		});
		
		btn_delete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				deleteStudent();	
				readStudent();
			}
		});		
		
		btn_update.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				updateStudent();
				readStudent();
			}
		});
		
		btn_add.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				readStudent();
				insertStudent();
				
			}
		});
		
	}
	
	public void deleteStudent() {
		try {
			int r = JOptionPane.showConfirmDialog(null, "정말로 삭제하시겠습니까?");
			if(r !=0) {
				return;
			}
			
			String name = jtf_name.getText();
			String sql  ="delete student where name='"+name+"'";
			
			Connection conn 
			= ConnectionProvider.getConnection();
			Statement stmt = conn.createStatement();
			int re = stmt.executeUpdate(sql);
			if(re > 0) {
				JOptionPane.showMessageDialog(null, "삭제성공");
				jtf_name.setText("");
				jtf_kor.setText("");
				jtf_eng.setText("");
				jtf_math.setText("");
			}else {
				JOptionPane.showMessageDialog(null, "삭제실패");
			}
			
			ConnectionProvider.close(stmt, conn);
			
		}catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		}
	}
	
	public void updateStudent() {
		try {
			String name = jtf_name.getText();
			int kor = Integer.parseInt(jtf_kor.getText());
			int eng = Integer.parseInt(jtf_eng.getText());
			int math = Integer.parseInt(jtf_math.getText());
			
			String sql = "update student set kor="+kor+",eng="+eng+",math="+math+" where name='"+name+"'";
			Connection conn 
			= ConnectionProvider.getConnection();
			Statement stmt = conn.createStatement();
			int re = stmt.executeUpdate(sql);
			if(re > 0) {
				JOptionPane.showMessageDialog(null, "수정성공!");
			}else {
				JOptionPane.showMessageDialog(null, "수정실패!");
			}
			ConnectionProvider.close(stmt, conn);
			
		}catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		}
	}
	
	
	public void insertStudent() {
		try {
			String name = jtf_name.getText();
			int kor = Integer.parseInt(jtf_kor.getText());
			int eng = Integer.parseInt(jtf_eng.getText());
			int math = Integer.parseInt(jtf_math.getText());
			
			String sql = "insert into student values('"+name+"',"+kor+","+eng+","+math+")";
			Connection conn 
			= ConnectionProvider.getConnection();
			Statement stmt = conn.createStatement();
			int re = stmt.executeUpdate(sql);
			if(re > 0) {
				JOptionPane.showMessageDialog(null, "등록성공!");
			}else {
				JOptionPane.showMessageDialog(null, "등록실패!");
			}
			ConnectionProvider.close(stmt, conn);
			
		}catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		}
	}
	
	public void readStudent() {
		try {
			String sql = "select name,kor,eng,math from student";
			
			Connection conn = 
					ConnectionProvider.getConnection();
			
			//3. sql명령을 실행 Statement객체를 생성한다.
			Statement stmt = conn.createStatement();
			
			//4. sql명령을 실행한다.
			ResultSet rs  = stmt.executeQuery(sql);
			
			rowData.clear();
			
			while( rs.next()  ) {
				String name = rs.getString(1);
				int kor = rs.getInt(2);
				int eng = rs.getInt(3);
				int math = rs.getInt(4);
				int tot = kor + eng + math;
				double avg = tot/3.0;				
				Vector<String> v = new Vector<String>();
				v.add(name);
				v.add(kor+"");
				v.add(eng+"");
				v.add(math+"");
				v.add(tot+"");
				v.add(String.format("%.1f", avg));
				
				rowData.add(v);
			}
			
			table.updateUI();
			
			//5. 사용했던 자원들을 닫아줍니다.
			ConnectionProvider.close(rs,stmt,conn);
			
			
		}catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		}
	}
	
	public static void main(String[] args) {
		new StudentAllTable_TOTAVG();
	}
}














