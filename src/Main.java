
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

public class Main {
	static ArrayList<Package> packageArray = new ArrayList<>();
	static String word = "ISHOp";
	static char[] message = word.toCharArray();

	public static void sendRequest(Package pc) throws IOException {
		URL server = new URL("http://localhost:8080/?"
							 + "Name=" + pc.getNamePackage() + "&Char=" + pc.getMessage()
							 + "&Weight=" + pc.getWeightPackage() + "&Time=" + (pc.getTimeLifePackage() - 1)
							 + "&Date=" + pc.getTimeCreatePackage());
		InputStream is = server.openStream();
	}

	static void pushPackage(Package pc) {
		packageArray.add(pc);
	}

	private static String getNowDate() {
		Date now = new Date();
		LocalDateTime nowDate = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formatDateTime = nowDate.format(formatter);
		return formatDateTime;
	}

	private static void createPackage() {
		for (int i = 0; i < message.length; i++) {
			Package objectPackage = new Package(message[i],"Package№" + i,
												16, 64, getNowDate());
			pushPackage(objectPackage);
		}
	}

	public static void printInfoPackage() {
		for (Package i: packageArray) {
			System.out.println("Package: {" + "Name: " + i.getNamePackage() + ", Char: " + i.getMessage()
								+ ", Weight: " + i.getWeightPackage() + ", TimeLife: " + i.getTimeLifePackage()
								+ ", CreateTime: " + i.getTimeCreatePackage() + "}");
		}
	}

	public static void createNewRequest() throws IOException {
		for (Package i: packageArray) {
			sendRequest(i);
		}
	}

	public static void main(String[] args) throws IOException {
		Server server = new Server();
		server.start();
		createPackage();
		printInfoPackage();
		createNewRequest();
	}
}