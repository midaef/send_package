
import java.net.URL;
import java.util.Date;
import java.util.Scanner;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;
import java.time.format.DateTimeFormatter;

/*
Be sure to open port "8080".
For localhost:
	If you want send message in your localhost, then you must uncomment 2 lines in main
	and you must write in HOST "localhost".
 */

public class Main {
	static char[] message;
	static String url = "";
	static String port = "";
	static String word = "";
	static Integer countErrors = 0;
	static ArrayList<Package> packageArray = new ArrayList<>();

	public static void inputData() {
		System.out.print("Host: ");
		url = new Scanner(System.in).nextLine();
		System.out.print("Port: ");
		port = new Scanner(System.in).nextLine();
		System.out.print("Message: ");
		word = new Scanner(System.in).nextLine();
		message = word.toCharArray();
	}

	private static void createPackage() {
		for (int i = 0; i < message.length; i++) {
			Package objectPackage = new Package(message[i],"Package№" + i,
					16, 64, getNowDate());
			pushPackage(objectPackage);
		}
	}

	public static void pushPackage(Package pc) {
		packageArray.add(pc);
	}

	public static void printInfoPackage() {
		for (Package i: packageArray) {
			System.out.println("Package: {" + "Name: " + i.getNamePackage() + ", Char: " + i.getMessage()
					+ ", Weight: " + i.getWeightPackage() + ", TimeLife: " + i.getTimeLifePackage()
					+ ", CreateTime: " + i.getTimeCreatePackage() + "}");
		}
	}

	public static void createNewRequest() throws IOException, InterruptedException {
		System.out.println("The packets that were sent: ");
		for (Package i: packageArray) {
			sendRequest(i);
		}
		System.out.println("Errors: " + countErrors);
	}

	public static void sendRequest(Package pc) throws IOException, InterruptedException {
		try {
			URL server = new URL("http://" + url + ":" + port + "?"
					+ "Name=" + pc.getNamePackage() + "&Char=" + pc.getMessage()
					+ "&Weight=" + pc.getWeightPackage() + "&Time=" + (pc.getTimeLifePackage() - 1)
					+ "&Date=" + pc.getTimeCreatePackage());
			InputStream is = server.openStream();
			System.out.println(pc.getNamePackage());
		} catch (Exception e) {
			System.out.print("Host not found!\n");
			TimeUnit.SECONDS.sleep(5);
			countErrors++;
		}
	}

	private static String getNowDate() {
		Date now = new Date();
		LocalDateTime nowDate = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formatDateTime = nowDate.format(formatter);
		return formatDateTime;
	}

	public static void main(String[] args) throws IOException, InterruptedException {
//		Server server = new Server();
//		server.start();
		inputData();
		createPackage();
		printInfoPackage();
		createNewRequest();
	}
}