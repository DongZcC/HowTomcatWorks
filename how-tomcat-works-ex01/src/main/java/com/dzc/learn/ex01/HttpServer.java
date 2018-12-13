package com.dzc.learn.ex01;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 简单版本web 服务器
 */
public class HttpServer {


    public static final String WEB_ROOT = System.getProperty("user.dir") + File.separator + "webroot";

    public static void main(String[] args) {
        HttpServer server = new HttpServer();
        server.start();
    }

    private void start() {
        try {
            // 本机 8080 端口开启监听
            ServerSocket serverSocket = new ServerSocket(8080, 1, InetAddress.getByName("127.0.0.1"));

            // 获取连接
            while (true) {
                Socket socket = serverSocket.accept();
                InputStream input = socket.getInputStream();
                OutputStream output = socket.getOutputStream();
                Request request = new Request(input);
                request.parse();


                Response response = new Response(output);
                response.setRequest(request);
                response.sendStaticResource();

                // 关闭 socket
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
