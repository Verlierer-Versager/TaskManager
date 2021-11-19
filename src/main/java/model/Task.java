package model;

import lombok.Getter;
import model.Status;

import java.io.FileInputStream;

public final class Task {
    @Getter
    public final int id;
    public final String name;
    public final String description;
    public final Status status;
    public final int owner_id;

    public Task(String name, Status status, String description, int owner_id) {
        this.id = -1;
        this.name = name;
        this.description = description;
        this.status = status;
        this.owner_id = owner_id;
    }

    public Task(int id, String name, Status status, String description, int owner_id) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
        this.owner_id = owner_id;
    }


}
