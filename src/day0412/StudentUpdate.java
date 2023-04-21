package day0412;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;

// 수정할 학생의 이름,국어,영어,수학을 입력받아
// 입력정보로 데이터베이스를 수정하는 프로그램을 작성
public class StudentUpdate {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String name;
		int kor,eng,math;
		System.out.println("수정할 학생의 이름==>");
		name = sc.next();
		System.out.println("국어==>");
		kor = sc.nextInt();
		System.out.println("영어==>");
		eng = sc.nextInt();
		System.out.println("수학==>");
		math = sc.nextInt();
		
		//수정을 위한 명령어를 만들어 봅니다.
		String sql = 
		"update student set kor="+kor+
		",eng="+eng+",math="+math+
		" where name='"+name+"'";
		
		try {
			//1. jdbc드라이버를 메모리로 로드한다.
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//2. DB서버에 연결한다.
			String url = "jdbc:oracle:thin:@localhost:1521:XE";
			String user= "c##sist";
			String pwd = "sist";
			Connection conn 
			= DriverManager.getConnection(url, user, pwd);
			
			//3. sql명령을 실행하기 위한 Statement객체를 생성
			Statement stmt = conn.createStatement();
			
			//4. sql명령을 실행한다.
			int re = stmt.executeUpdate(sql);
			
			if(re > 0) {
				System.out.println("수정하였습니다.");
			}else {
				System.out.println("수정에 실패하였습니다.");
			}
			
			//5. 사용했던 자원을 닫아줍니다.
			stmt.close();
			conn.close();
			
		}catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		}
		
	}
}










