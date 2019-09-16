package pl.kasztany.orlenfirstaid.model;

import android.graphics.drawable.Drawable;

public class AlarmCallData {

    Drawable image;
    String number;
    String description;

    public AlarmCallData(Drawable image, String number, String description) {
        this.image = image;
        this.number = number;
        this.description = description;
    }

    public Drawable getImage() {
        return image;
    }

    public void setImage(Drawable image) {
        this.image = image;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
