package com.example.project.server;

import java.util.ArrayList;
import java.util.List;

public class BlackList {
    private List<String> blackList;
    private boolean check = false;

    public BlackList() {
        blackList = new ArrayList<>();
    }

    public void addBlcakList(String id) {
        blackList.add(id);
    }

    public boolean checkList(String id) {
        for (String s : blackList) {
            if (id.equals(s)) {
                check = true;
            }
        }
        return check;
    }
}
