package com.example.project.node;

public class RequestParsingNode {
    String url;
    String host;
    String path;
    String id;
    String deviceId;
    String type;

    String count;
    String startTime;
    String endTime;

    public RequestParsingNode(String url) {
        this.url = url;
    }

    public void requestParsig(String url) {
        // http://ems.nhnacademy.com:1880/dev
        String[] fields = url.split("://");

        String[] fields2 = fields[1].split("/");

        host = fields2[0];
        path = fields2[1];

        if (fields2.length == 3) {
            deviceId = fields2[2];
        }


        if (fields2.length == 4) {
            type = fields2[2];
            id = fields2[3];
            if (id.contains("?")) {
                deviceId = id.split("\\?")[0];
                String fields3 = id.split("\\?")[1];

                String[] options = fields3.split("&");

                count = options[0].substring(6);
                startTime = options[1].substring(3);
                endTime = options[2].substring(3);

            } else {
                deviceId = id;
            }

            System.out.println(count);
            System.out.println(startTime);
            System.out.println(endTime);
        }
    }

    public String getHost() {
        return host;
    }

    public String getPath() {
        return path;
    }

    public String getType() {
        return type;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public static void main(String[] args) {

        String url =
                "http://ems.nhnacademy.com:1880/ep/temperature/24e124126c457594?count=40&st=1696772438&et=1696772438";

        RequestParsingNode node = new RequestParsingNode(url);
        node.requestParsig(url);

        System.out.println(node.getHost());
        System.out.println(node.getPath());
        System.out.println(node.getType());
        System.out.println(node.getDeviceId());

    }
}
