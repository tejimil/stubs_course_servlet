package calc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
//import java.io.UnsupportedEncodingException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import calc.logic.*;

@WebServlet(urlPatterns = "/get")
public class calculator extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//calculations calc = calculations.getInstance();
	calculations calc = new calculations();
	Gson gson = new GsonBuilder().setPrettyPrinting().create();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		StringBuffer jb = new StringBuffer();
		String line;
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
		
		double a = jobj.get("a").getAsDouble();
		double b = jobj.get("b").getAsDouble();
		//String sign = jobj.get("sign").getAsString();
		@SuppressWarnings("deprecation")
		char sign = jobj.get("sign").getAsCharacter();
		
		calc.docalc(a, b, sign);
		
		response.setContentType("application/json:charset=utf-8");
		
		PrintWriter pw = response.getWriter();
		
		pw.print(gson.toJson(calc.getRes()));
	}

}
