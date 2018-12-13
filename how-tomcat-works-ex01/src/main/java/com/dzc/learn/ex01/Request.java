package com.dzc.learn.ex01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Request {

    private InputStream input;

    private String uri;

    public Request(InputStream input) {
        this.input = input;
    }

    public void parse() {
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(input));
            String requestHead = bf.readLine();
            uri = parseUri(requestHead);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private String parseUri(String requestString) {
        int index1, index2;
        index1 = requestString.indexOf(' ');
        if (index1 != -1) {
            index2 = requestString.indexOf(' ', index1 + 1);
            if (index2 > index1)
                return requestString.substring(index1 + 1, index2);
        }
        return null;
    }


    public String getUri() {
        return uri;
    }
}
