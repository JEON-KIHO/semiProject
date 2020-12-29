package playlist;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


@WebServlet(value={"/playlist.json","/chart.json"})
public class CrServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		int i=1;
		int page=request.getParameter("page")==null? 1: Integer.parseInt(request.getParameter("page"));
		
		
		int start = ((page-1) * 9) + 1;
		int end = page * 9;
		
		switch(request.getServletPath()) {
		case "/playlist.json":
			JSONArray jarray=new JSONArray();
			
			
			try {
				Document doc=Jsoup.connect("https://www.melon.com/").get();
				
				Elements s = doc.select(".checkEllipsis");
				ArrayList<String> aArray = new ArrayList<String>();
				for (Element e:s) {
					String singer = e.text();
					aArray.add(singer);
				}
				
				Elements es=doc.select(".img");
				ArrayList<String> iArray = new ArrayList<String>();
				for(Element e:es) {
					String image=e.select("img").attr("src");
					iArray.add(image);
					
				}
				
				for(int z=0; z<iArray.size(); z++) {
					if(i >= start && i <= end) {
						JSONObject job=new JSONObject();
						job.put("i", i);
						job.put("image", iArray.get(z));
						job.put("singer", aArray.get(z));
						
						jarray.add(job);
					}
					i++;
				}
				
				out.println(jarray.toJSONString());
			}catch(Exception e) {
				System.out.println(e.toString());
			}
			break;
		case "/chart.json":
			jarray=new JSONArray();
			int cstart = ((page-1) * 9) + 1;
			int cend = page * 9;
			try {
				Document doc=Jsoup.connect("https://www.melon.com/chart/index.htm").get();
				
				Elements s=doc.select(".checkEllipsis");
				ArrayList<String> aArray = new ArrayList<String>();
				for (Element e:s) {
					String artist = e.text();
					aArray.add(artist);
				}
				
				Elements ss = doc.select(".rank01");
				ArrayList<String> bArray = new ArrayList<String>();
				for (Element e:ss) {
					String album = e.text();
					bArray.add(album);
				}
				
				Elements es=doc.select(".image_typeAll");
				ArrayList<String> iArray = new ArrayList<String>();
				for(Element e:es) {
					String image=e.select("img").attr("src");
					iArray.add(image);
				}
				for(int z=0; z<iArray.size(); z++) {
					
					if(i >= cstart && i <= cend) {
					/*String album=e.select(".song .mlog").text();
					}
					String artist=e.select(".checkEllipsisRealtimeChart .mlog").text();
					String image=e.select("img").attr("src");*/
					
					JSONObject job=new JSONObject();
					job.put("i", i);
					job.put("album",bArray.get(z));
					job.put("artist", aArray.get(z));
					job.put("image", iArray.get(z));
					jarray.add(job);
				}
					i++;
				}
				out.println(jarray.toJSONString());
			}catch(Exception e) {
				System.out.println(e.toString());
			}
			break;
		
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
