package crawling;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
	 
	public class KospiCrawer {
	    private static final String CONNECT_URL ="https://finance.naver.com/";
	    
	    // 현재 코스피지수
	    public String getKospi() throws Exception {
	        Document document = Jsoup.connect(CONNECT_URL).get();
	        Elements kospi = document.select("#content > div.article > div.section2 > div.section_stock_market > div.section_stock > div.kospi_area.group_quot.quot_opn > div.heading_area > a > span > span.num");
	        return kospi.text();
	    }
	 
		// 코스피 변동지수
	    public String getKospiChangeRate() throws Exception {
	        Document document = Jsoup.connect(CONNECT_URL).get();
	        Elements kospiChangedRate = document.select("#content > div.article > div.section2 > div.section_stock_market > div.section_stock > div.kospi_area.group_quot.quot_opn > div.heading_area > a > span > span.num2");
	        return kospiChangedRate.text();
	    }
	    
	}