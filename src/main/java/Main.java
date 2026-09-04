// 순수 자바로 서버 / hello 보여주기
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class Main {
    public static void main(String[] args) throws IOException {
        HttpServer httpServer = HttpServer.create(
                new InetSocketAddress(9090),0);
        httpServer.createContext("/hello",exchange -> {
            String response = "hello";
            exchange.sendResponseHeaders(200,response.getBytes().length);
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        });

        httpServer.setExecutor(null);
        httpServer.start();
        System.out.println("서버 시작 : http://localhost:9090/hello");
    }
}
