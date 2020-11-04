package View;


import java.sql.*;
import java.util.*;

public class Mainview {
	public static Calendar cal = Calendar.getInstance();
	public static Connection conn=null;
	public static Statement stmt=null;
	public static ResultSet rs=null;
	public static PreparedStatement pstmt=null;
	public static ArrayList<String> ID=new ArrayList<String>();
	public static ArrayList<String> PW=new ArrayList<String>(); 
	public static ArrayList<Integer> Num=new ArrayList<Integer>();
	public static String id;
	public static String pw;
	public static int num;
	public static int index;
	
	public static Scanner sc=new Scanner(System.in);
	public static void main(String[] args) {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url="jdbc:mysql://localhost/practice?characterEncoding=UTF-8&serverTimezone=UTC";
			conn=DriverManager.getConnection(url,"root","password");
			stmt=conn.createStatement();
			String sql="SELECT no,ID,PW FROM attendance";
			rs=stmt.executeQuery(sql);
			while(rs.next()) {
				Num.add(rs.getInt(1));
				ID.add(rs.getString(2));
				PW.add(rs.getString(3));
				System.out.println(Num);
			}
			index=ID.indexOf("id3"); //얼굴인식
			num=Num.get(index);
			id=ID.get(index);
			pw=PW.get(index);
			
			System.out.println("1.로그인 2.회원가입");
			int select=sc.nextInt();
			if(select==1) {
				Login();
			}else if(select==2) {
				CreateAdmin();
			}
			
		}
		catch(ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
		}catch(SQLException e) {
			System.out.println("에러: "+e);
		}
		System.out.println("1.출근 2.퇴근 3.관리");
		int select=sc.nextInt();
		
		switch(select) {
		case 1:Check_in(num);
		break;
		case 2:Check_out(num);
		break;
		case 3:RunManage();
		break;
		}
	}
	
	

	public static void Check_in(int num) {
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		int min = cal.get(Calendar.MINUTE);
		int[] start= {hour,min};
		System.out.println(hour + "시 " + min + "분 출근하였습니다.");
	
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url="jdbc:mysql://localhost/practice?characterEncoding=UTF-8&serverTimezone=UTC";
			conn=DriverManager.getConnection(url,"root","password");
			
			String sql="UPDATE attendance SET start_hour=?,start_min=?  WHERE no=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1,hour);
			pstmt.setInt(2,min);				
			pstmt.setInt(3,num);

			int count=pstmt.executeUpdate();
			if(count==0) {
				System.out.println("데이터 입력 실패");
			}else {
				System.out.println("데이터 입력 성공");
			}
		}
		catch(ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
		}catch(SQLException e) {
			System.out.println("에러: "+e);
		}
	
	
	
	
	}
	public static void Check_out(int num) {
		
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		int min = cal.get(Calendar.MINUTE);
		int[] start= {hour,min};
		System.out.println(hour + "시 " + min + "분 퇴근하였습니다.");
	
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url="jdbc:mysql://localhost/practice?characterEncoding=UTF-8&serverTimezone=UTC";
			conn=DriverManager.getConnection(url,"root","password");
			
			String sql="UPDATE attendance SET end_hour=?, end_min=?  WHERE no=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1,hour);
			pstmt.setInt(2,min);				
			pstmt.setInt(3,num);

			int count=pstmt.executeUpdate();
			if(count==0) {
				System.out.println("데이터 입력 실패");
			}else {
				System.out.println("데이터 입력 성공");
			}
		}
		catch(ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
		}catch(SQLException e) {
			System.out.println("에러: "+e);
		}
	}
	public static void RunManage() {
		System.out.println("ID입력 :");
		String newID=sc.next();
		
		System.out.println("PW입력 :");
		String newPW=sc.next();
		if(!(newID.equals("admin") && newPW.equals("admin"))) {
			System.out.println("로그인실패");
			System.exit(0);
			}
		
		System.out.println("1.사원삭제 2.사원추가");
		int select=sc.nextInt();
		switch(select) {
		case 1:
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				String url="jdbc:mysql://localhost/practice?characterEncoding=UTF-8&serverTimezone=UTC";
				conn=DriverManager.getConnection(url,"root","password");
				System.out.println("삭제할 사원 선택");
				select=sc.nextInt();
				String sql="DELETE "
						+ "FROM attendance WHERE no=?";
				pstmt=conn.prepareStatement(sql);
				pstmt.setInt(1,select);
				
				
				
				int count=pstmt.executeUpdate();
				if(count==0) {
					System.out.println("데이터 입력 실패");
				}else {
					System.out.println("데이터 입력 성공");
				}
			}
			catch(ClassNotFoundException e) {
				System.out.println("드라이버 로딩 실패");
			}catch(SQLException e) {
				System.out.println("에러: "+e);
			}
			
			break;
		case 2:
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				String url="jdbc:mysql://localhost/practice?characterEncoding=UTF-8&serverTimezone=UTC";
				conn=DriverManager.getConnection(url,"root","password");
				System.out.println("ID입력 :");
				newID=sc.next();
				
				System.out.println("PW입력 :");
				newPW=sc.next();
				String sql="INSERT INTO attendance VALUES(?,?,?,?,?,?,?)";
						
				pstmt=conn.prepareStatement(sql);
				pstmt.setInt(1,Num.get(Num.size()-1)+1); //비어있는 곳 or 마지막 다음
				pstmt.setString(2, newID);
				pstmt.setString(3, newPW);
				pstmt.setInt(4,0);				
				pstmt.setInt(5,0);
				pstmt.setInt(6,0);
				pstmt.setInt(7,0);
				
				int count=pstmt.executeUpdate();
				if(count==0) {
					System.out.println("데이터 입력 실패");
				}else {
					System.out.println("데이터 입력 성공");
				}
			}
			catch(ClassNotFoundException e) {
				System.out.println("드라이버 로딩 실패");
			}catch(SQLException e) {
				System.out.println("에러: "+e);
			}

			break;
		}
		
	}
	public static boolean Login() {
		System.out.println("ID입력 :");
		String newID=sc.next();
		
		System.out.println("PW입력 :");
		String newPW=sc.next();
		if(!(newID.equals("admin") && newPW.equals("admin"))) {
			System.out.println("로그인실패");
			System.exit(0);
			return false;
			}
		else return true;
	}
	public static void CreateAdmin() {
		System.out.println("사원번호 :");
		int sno=sc.nextInt();
		index=Num.indexOf(sno);
		String newID=ID.get(index);
		String newPW=PW.get(index);
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url="jdbc:mysql://localhost/practice?characterEncoding=UTF-8&serverTimezone=UTC";
			conn=DriverManager.getConnection(url,"root","password");
			
			String sql="INSERT INTO logindb VALUES(?,?,?)";
					
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setInt(1,sno); 
			pstmt.setString(2, newID );
			pstmt.setString(3, newPW);
			
			int count=pstmt.executeUpdate();
			if(count==0) {
				System.out.println("데이터 입력 실패");
			}else {
				System.out.println("데이터 입력 성공");
			}
		}
		catch(ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
		}catch(SQLException e) {
			System.out.println("에러: "+e);
		}
	}
	
	
	

}
