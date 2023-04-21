package exam01;

import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class VectorTest extends JFrame {
	//엑셀과 같은 화면을 위한 JTable변수를 선언합니다.
	JTable table;
	
	//테이블의 칼럼이름을 담기 위한 벡터를 선언해요.
	Vector<String> colNames;
	
	//테이블의 데이터를 담기 위한 벡터를 선언해요.
	//테이블의 데이터 하나는 벡터로 표현됩니다.
	//그래서 테이블의 전체 데이터는 백터를 담는 벡터입니다.
	Vector<Vector<String>> rowData;
	
	public VectorTest(){
		
		//테이블의 칼럼이름을 담기 위한 벡터를 생성합니다.
		colNames = new Vector<String>();
		
		//테이블의 데이터를 담기 위한 벡터를 생성합니다.
		rowData = new Vector<Vector<String>>();
		
		//테이블의 칼럼이름을 담기 위한 벡터에 데이터를 추가합니다.
		colNames.add("상품명");
		colNames.add("가격");
		colNames.add("수량");
		
		//테이블의 데이터를 위한 세개의 벡터를 생성한다.
		Vector<String> v1 = new Vector<String>();
		Vector<String> v2 = new Vector<String>();
		Vector<String> v3 = new Vector<String>();
		
		//첫번째 데이터를 위한 벡터에 데이터를 추가합니다.
		v1.add("색종이");
		v1.add("700");
		v1.add("5");
		
		//두번째 데이터를 위한 벡터에 데이터를 추가합니다.
		v2.add("삼각자");
		v2.add("1000");
		v2.add("2");
		
		//세번째 데이터를 위한 벡터에 데이터를 추가합니다.
		v3.add("딱풀");
		v3.add("500");
		v3.add("10");
		
		//테이블의 데이터를 담기 위한 벡터에 세게의 데이터를 차례로 추가합니다.
		rowData.add(v1);
		rowData.add(v2);
		rowData.add(v3);
		
		//자료가 담긴백터와 칼럼이름이 담긴 벡터를 갖고 테이블을 만들어요.
		table = new JTable(rowData, colNames);
		
		//테이블의 스클로바 표시를 위하여 스크롤팬 객체를 생성합니다.
		JScrollPane jsp = new JScrollPane(table);
		
		
		add(jsp);
		setSize(400, 300);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}

	public static void main(String[] args) {
		new VectorTest();

	}

}
