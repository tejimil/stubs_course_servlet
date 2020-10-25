package ru.appline;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
//import java.io.UnsupportedEncodingException;
//import java.util.Map;

//import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import ru.appline.logic.Model;
//import ru.appline.logic.User;

@WebServlet(urlPatterns = "/get")
public class ServletList extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Model model = Model.getInstance();
	Gson gson = new GsonBuilder().setPrettyPrinting().create();
	
	/*protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=utf-8");
		//request.setCharacterEncoding("UTF-8");
		
		PrintWriter pw = response.getWriter();
		
		int id = Integer.parseInt(request.getParameter("id"));
		
		if(id==0) {
			pw.print("<html>"+
					"<h3>��������� ������������:</h3><br/>"
					+ "ID ������������: "
					+"<ul>");
			
			for (Map.Entry<Integer, User> entry: model.getFromList().entrySet()) {
				pw.print("<li>"+entry.getKey()+"</li>"+
						"<ul>"+
						"<li>���: "+entry.getValue().getName()+"</li>"+
						"<li>�������: "+entry.getValue().getSurname()+"</li>"+
						"<li>��������: "+entry.getValue().getSalary()+"</li>"+
						"</ul>");
			}
			pw.print("</ul>"+"<a href=\"index.jsp\">�����</a>"+"</html>");
			
		}else if(id>0) {
			if(id>model.getFromList().size()) {
				pw.print("<html>"+
						"<h3>������ ������������ ���</h3>"+
						"<a href=\"index.jsp\">�����</a>"+"</html>");
			}else {
				pw.print("<html>"+
						"<h3>����������� ������������:</h3>"+
						"<br/>"+
						"���: "+model.getFromList().get(id).getName()+"<br/>"+
						"�������: "+model.getFromList().get(id).getSurname()+"<br/>"+
						"��������: "+model.getFromList().get(id).getSalary()+"<br/>"+
						"<a href=\"index.jsp\">�����</a>"+"</html>");
			}
		}else {
			pw.print("<html>"+
					"<h3>ID ������ ���� ������ ����</h3>"+
					"<a href=\"index.jsp\">�����</a>"+"</html>");
		}
	}*/
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
		StringBuffer jb = new StringBuffer();
		String line;
		
		PrintWriter pw = response.getWriter();
		
		response.setContentType("application/json:charset=utf-8");
		
		
		try {
			BufferedReader reader = request.getReader();
			while((line = reader.readLine())!=null) {
				jb.append(line);
			}
		}catch (Exception e) {
			System.out.println("Error");
		}
		
		JsonObject jobj = gson.fromJson(String.valueOf(jb), JsonObject.class);
		request.setCharacterEncoding("UTF-8");
		
		 int id = jobj.get("id").getAsInt();
		 
		if(id==0) {
			
			pw.print(gson.toJson(model.getFromList()));
			
		}else if(id>0) {
			
			if(id>model.getFromList().size()) {
				
				pw.print("no such user");
				
			}else {
				
				pw.print(gson.toJson(model.getFromList().get(id)));
			}
			
		}else {
			pw.print("no minus");
		}
		 
		
	}
}
