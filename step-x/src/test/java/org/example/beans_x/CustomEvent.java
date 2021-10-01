package org.example.beans_x;

import org.example.context.ApplicationEvent;

public class CustomEvent extends ApplicationEvent {
    private Long id;
    private String message;

    public CustomEvent(Object o,Long id,String message) {
        super(o);
        this.id =id;
        this.message =message;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
