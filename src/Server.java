
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread {

    @Override
    public void run() {
        ServerSocket server = null;
        try {
            server = new ServerSocket(8080);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Listening for connection on port 8080 ....");
        while (true) {
            try (Socket socket = server.accept()){
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String line = reader.readLine().replace("GET /?", "")
                                                        .replace(" HTTP/1.1", "");
				if (!line.equals("GET /favicon.ico")){
					Package pc = getData(line);
					System.out.println(pc);
				}
                String httpResponse = "HTTP/1.1 200 OK\r\n\r\n" + "Server started";
                socket.getOutputStream().write(httpResponse.getBytes("UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

	public static Package getData(String request) {
		String[] dataArray = request.split("&");
		Package pc = new Package(dataArray[1].split("=")[1].charAt(0),
				dataArray[0].split("=")[1],
				Integer.parseInt(dataArray[2].split("=")[1]),
				Integer.parseInt(dataArray[3].split("=")[1]),
				dataArray[4].split("=")[1]);
		return pc;
	}

}
