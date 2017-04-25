
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

import org.random.api.RandomOrgClient;
import org.random.api.exception.RandomOrgBadHTTPResponseException;
import org.random.api.exception.RandomOrgInsufficientBitsError;
import org.random.api.exception.RandomOrgInsufficientRequestsError;
import org.random.api.exception.RandomOrgJSONRPCError;
import org.random.api.exception.RandomOrgKeyNotRunningError;
import org.random.api.exception.RandomOrgRANDOMORGError;
import org.random.api.exception.RandomOrgSendTimeoutException;


public class TestDiceRoll {
	public static void main(String[] args) {
		HashMap<Integer, Integer> result = new HashMap<>();
		Scanner scan = new Scanner(System.in);
		System.out.println("How many dice: ");
		int dice = scan.nextInt();
		System.out.println("How many rolls: ");
		int rolls = scan.nextInt();
		System.out.println("How many sides: ");
		int sides = scan.nextInt();
		scan.close();
		/*HashMap<Integer, Integer> result = new HashMap<>();
		for (int i = 0; i < rolls; i++) {
			int random = ThreadLocalRandom.current().nextInt((dice), (dice * sides) + 1);
			if (result.get(random) != null)
				result.put(random, result.get(random) + 1);
			else
				result.put(random, 1);
		}
		URL url;
		try {
			url = new URL(
			          "http://www.random.org/strings/?num=10&len=10&digits=on&unique=on&format=plain&rnd=new");
			 HttpURLConnection connection = (HttpURLConnection) url.openConnection(); 
			 connection.addRequestProperty("User-Agent", "Mozilla/4.76"); 
			 //System.setProperty("http.agent", "Chrome");
		     // URLConnection connection = url.openConnection();
		      connection.setConnectTimeout(5000);
		      final BufferedReader in = new BufferedReader(new InputStreamReader(
		          connection.getInputStream()));

		      final StringBuilder stringBuilder = new StringBuilder();

		      String line = in.readLine();
		      while ((line = in.readLine()) != null) {
		        stringBuilder.append(line);
		        System.out.println(line);
		      }
		      in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		Random rng = new Random();
		double r = Math.sqrt(-2 * Math.log(rng.nextDouble()));
		double θ = 2 * Math.PI * rng.nextDouble();
		double x = r * Math.cos(θ);
		double y = r * Math.sin(θ);
		System.out.println(x + y);
		RandomOrgClient roc = RandomOrgClient.getRandomOrgClient("0f0036cd-513c-4e31-becd-a5d26de8ead4");
		        int[] randoms = null;
				try {
					randoms = roc.generateIntegers(rolls, dice, (dice * sides));
				} catch (RandomOrgSendTimeoutException | RandomOrgKeyNotRunningError
						| RandomOrgInsufficientRequestsError | RandomOrgInsufficientBitsError
						| RandomOrgBadHTTPResponseException | RandomOrgRANDOMORGError | RandomOrgJSONRPCError
						| IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				for (int i = 0; i < randoms.length; i++) {
					if (result.get(randoms[i]) != null)
						result.put(randoms[i], result.get(randoms[i]) + 1);
					else
						result.put(randoms[i], 1);
				}
		        //System.out.println(Arrays.toString(randoms));
		
		
		for (Map.Entry<Integer, Integer> i : result.entrySet()) 
			System.out.println(i.getKey() + ": " + i.getValue());
			
	}
}
