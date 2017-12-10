package myservlet.control;
import mybean.data.DataByPage;
import com.sun.rowset.*;
import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class QueryAllRecord extends HttpServlet {
	CachedRowSetImpl rowSet=null;
	public void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		request.setCharacterEncoding("utf-8");
		String idNumber=request.getParameter("fenleiNumber");
		if(idNumer==null)
			idNumber="0";
		int id=Integer.parsetInt(inNumber);
		HttpSession session=request.getSession(true);
		Connection con=null;
		DataByPage dataBean=null;
		try {
			dataBean=(DataByPage)session.getAttribute("dataBean");
			if(dataBean=null) {
				dataBean=new DataByPage();  //创建JavaBean对象
				session.setAttribute("dataBean",dataBean );
			}
		}
		catch(Exception exp) {
			dataBean=new DataByPage();  //创建JavaBean对象
			session.setAttribute("dataBean",dataBean );
		}
		String uri="jdbc:mysql://127.0.0.1/shop";
		try {
			con=DriverManager.getConnection(uri,"root","");
			Statement sql=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs=sql.executeQuery("SELECT*FROM cosmeticForm where id="+id);
			rowSet=new CachedRowSetImpl();    //创建行集对象
			rowSet.populate(rs);
			dataBean.setRowSet(rowSet);           //行集数据存储在dataBean中
			con.close();                                  //关闭连接
		}
		catch(SQLException exp) {}
		response.sendRedirect("byPageShow.jsp");//重定向到byPageShow.jsp
	}
	public void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		doPost(request,reponse);
	}

}
