package com.dzc.learn.ex01;

import org.apache.commons.lang3.StringUtils;

import java.io.*;

public class Response {

    private Request request;

    private OutputStream output;

    public Response(OutputStream output) {
        this.output = output;
    }

    public void setRequest(Request request) {
        this.request = request;
    }


    // 返回静态资源
    public void sendStaticResource() {
        try {
            if (request.getUri() == null)
                return;
            String fileName = StringUtils.substring(request.getUri(), 1);

            File file = new File(HttpServer.WEB_ROOT, fileName);
            if (file.exists()) {
                BufferedReader bf = new BufferedReader(new FileReader(file));
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = bf.readLine()) != null) {
                    sb.append(line);
                }
//                String msg = "HTTP/1.1 200 OK\r\n" +
//                        "Content-type= text/html\r\n" +
//                        "\r\n" +
//                        "\r\n" +
//                        sb.toString();
                output.write(sb.toString().getBytes());

            } else {
                String errorMsg = "HTTP/1.1 404 Not Found\r\n" +
                        "Content-type= text/html\r\n" +
                        "\r\n" +
                        "\r\n" +
                        "Not Found";

                output.write(errorMsg.getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
