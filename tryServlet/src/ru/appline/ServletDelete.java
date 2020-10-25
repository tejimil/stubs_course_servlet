package ru.appline;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import ru.appline.logic.Model;


@WebServlet(urlPatterns = "/delete")
public class ServletDelete extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Model model = Model.getInstance();
	Gson gson = new GsonBuilder().setPrettyPrinting().create();
	
	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException{
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
				
				//pw.print(gson.toJson(model.getFromList().get(id)));
				pw.print(gson.toJson(model.getFromList().remove(id)));
			}
			
		}else {
			pw.print("no minus");
		}
		 
		
	}
}
