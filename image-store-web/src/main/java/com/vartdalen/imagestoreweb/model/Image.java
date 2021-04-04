package com.vartdalen.imagestoreweb.model;

import com.vartdalen.imagestoreweb.model.enumerator.ImageCategory;
import com.vartdalen.imagestoreweb.model.enumerator.ImageExtension;
import com.vartdalen.imagestoreweb.model.enumerator.ImageOrientation;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Image {
    @NotNull
    private long id;

    @NotNull
    private String title;

    @NotNull
    private ImageOrientation orientation;

    @NotNull
    private Set<ImageCategory> categories;

    @NotNull
    private ImageExtension imageExtension;

    private LocalDateTime created;

    public Image() {}

    public Image(long id, String title, ImageOrientation orientation, ImageCategory[] categories, ImageExtension imageExtension) {
        this.id = id;
        this.title = title;
        this.orientation = orientation;
        this.categories = new HashSet<>(Arrays.asList(categories));
        this.imageExtension = imageExtension;
    }

    public long getId() { return id; }
    public String getTitle() { return title; }
    public ImageOrientation getOrientation() { return orientation; }
    public Set<ImageCategory> getCategories() { return categories; }
    public ImageExtension getImageExtension() { return imageExtension; }
    public LocalDateTime getCreated() { return created; }

    public void setId(long id) { this.id = id; }
    public void setTitle(String title) { this.title = title; }
    public void setOrientation(ImageOrientation orientation) { this.orientation = orientation; }
    public void setCategories(ImageCategory[] categories) { this.categories = new HashSet<>(Arrays.asList(categories)); }
    public void setImageExtension(ImageExtension imageExtension) { this.imageExtension = imageExtension; }
}

