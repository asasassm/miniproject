package com.example.project.server;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BooleanSupplier;

public class BlackList {
    private List<String> blackList;


    public BlackList() {
        blackList = new ArrayList<>();
    }

    public void addBlcakList(String ip) {
        blackList.add(ip);
    }

    public List<String> getList() {
        return blackList;
    }

    public boolean contain(String string) {
        return blackList.contains(string);
    }

    public boolean remove(String ip) {
        return blackList.remove(ip);
    }
}
