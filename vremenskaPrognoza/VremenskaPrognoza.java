package vremenskaPrognoza;

import java.util.Calendar;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.sun.jdi.Type;

public class VremenskaPrognoza {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		System.out.println("Dobrodošli u konzolnu vremensku prognozu!");
		System.out.println("-----------------------------------------");
		String komande = "Komande:\r\n" + "-1) Izlaz iz programa\r\n" + " 0) Promena grada [String]\r\n"
				+ " 1) Trenutna temperatura\r\n" + " 2) Prognoza za 5 dana\r\n" + "99) Ispis komandnog menija";
		System.out.println(komande);

		int unos = sc.nextInt();
		String city = "Belgrade";
		String cC = "RS";
		JSONParser parser = new JSONParser();
		while (unos != -1) {
			if (unos == 0) {
				System.out.println("Unesite grad u formatu: city, countryCode");
				city = sc.next();
				cC = sc.next();
			} else if (unos == 1) {
				String s = getWeatherInfo(city, cC, Type.CURRENT);
				JSONObject o1 = (JSONObject) parser.parse(s);
				JSONObject t = (JSONObject) o1.get("main");
				float trenutna = (float) t.get("temp");
				float maksimalna = (float) t.get("temp_max");
				float minimalna = (float) t.get("temp_min");
				System.out.println("Trenutna: " + trenutna + "°C\n" + "Maksimalna: " + maksimalna + "°C\n"
						+ "Minimalna: " + minimalna + "°C");

			} else if (unos == 2) {
				Calendar calendar = Calendar.getInstance();
				int index = calendar.get(Calendar.DAY_OF_WEEK) - 1;
				String[] dani = { "Ned", "Pon", "Uto", "Sre", "Čet", "Pet", "Sub" };
				for (int i = 0; i < 5; i++) {
					String s = getWeatherInfo(city, cC, Type.FORECAST);
					JSONObject o = (JSONObject) parser.parse(s);
					JSONArray t = (JSONArray) o.get("list");
					JSONObject z = (JSONObject) t.get(1);
					JSONObject y = (JSONObject) z.get("main");
					float temp = (float) y.get("temp");
					System.out.println(dani[index] + temp + " °C");
					index++;
					// ne valja ovo

				}
			} else if (unos == 99) {
				System.out.println(komande);
			}

		}
	}

}
