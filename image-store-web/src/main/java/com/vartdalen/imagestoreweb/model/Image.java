package com.vartdalen.imagestoreweb.model;

import java.sql.Date;

public class Image {
    private long id;
    private byte[] bytes;
    private Date created;

    public Image() {
    }

    public Image(long id, byte[] bytes, Date created) {
        this.id = id;
        this.bytes = bytes;
        this.created = created;
    }

    public long getId() {
        return id;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public Date getCreated() {
        return created;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}
