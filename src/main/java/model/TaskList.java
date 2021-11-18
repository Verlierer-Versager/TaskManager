package model;

import java.util.List;
import java.util.Map;

public class TaskList {
 private final Map<Integer, List<Task>> listMap;

    public TaskList(Map<Integer, List<Task>> listMap) {
        this.listMap = listMap;
    }

//    public TaskList() {
//        //listMap.put(0)
//    }
}
