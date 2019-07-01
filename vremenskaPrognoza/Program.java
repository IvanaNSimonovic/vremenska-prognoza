package vremenskaPrognoza;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class Program {
	
	static long lastRequestTimestamp;
	public static enum Type { CURRENT, FORECAST };
	public static String getWeatherInfo(String city, String countryCode, Type type) throws IOException {
	String authToken = "d0f1969fd9856fe09e3f7d0753d84ed4";
	String addr = String.format("http://api.openweathermap.org/data/2.5/%s?q=%s,%s&appid=%s&units=metric", type == Type.CURRENT ? "weather" : "forecast", city,
	countryCode,authToken );
	if(System.currentTimeMillis() - lastRequestTimestamp < 5000) {
	try {
	Thread.sleep(5000);
	} catch (InterruptedException e) {
	e.printStackTrace();
	}
	}
	URLConnection yc = new URL(addr).openConnection();
	BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
	StringBuilder buffer = new StringBuilder();
	String inputLine;
	while ((inputLine = in.readLine()) != null)
	buffer.append(inputLine);
	in.close();
	lastRequestTimestamp = System.currentTimeMillis();
	return buffer.toString();

}
}