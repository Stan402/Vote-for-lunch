package ru.graduateproject.to;

import org.springframework.data.domain.Persistable;
import ru.graduateproject.HasId;


public abstract class BaseTo implements HasId {
    protected Integer id;

    public BaseTo() {
    }

    public BaseTo(Integer id) {
        this.id = id;
    }

    @Override
    public Integer getId() {
        return id;
    }


    public void setId(Integer id) {
        this.id = id;
    }


}
