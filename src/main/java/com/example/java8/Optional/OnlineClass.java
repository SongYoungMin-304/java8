package com.example.java8.Optional;

import java.util.Optional;

public class OnlineClass {

    private Integer id;

    private String title;

    private boolean closed;

    private Progress progress;

    public OnlineClass(Integer id, String title, boolean closed) {
        this.id = id;
        this.title = title;
        this.closed = closed;
    }

    public Optional<Progress> getProgress() {
        /*if(this.progress == null){
            throw new IllegalStateException();
        }*/
        // 리턴값으로만 쓰기를 권장한다.
        return Optional.ofNullable(progress);
    }

/*    public void setProgress(Optional<Progress> progress) {
        progress.ifPresent((p) -> this.progress = p);
    }*/

    public void setProgress(Progress progress) {
        this.progress = progress;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }
}
