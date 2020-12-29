package crawling;

public class CrawlingApplication {
	 
    public static void main(String[] args) throws Exception {
        KospiCrawer kospiCrawer = new KospiCrawer();
        String currentKospi = kospiCrawer.getKospi();
        String changeKospiRate = kospiCrawer.getKospiChangeRate();
        System.out.println("현재 코스피 : " + currentKospi + " 변동율 : " + changeKospiRate);
    }
 
}