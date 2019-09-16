package pl.kasztany.orlenfirstaid.model;

public class CategoryContent {
    public CategoryContent(String titlePl, String contentType, String contentValue) {
        this.titlePl = titlePl;
        this.contentType = contentType;
        this.contentValue = contentValue;
    }

    private String titlePl;
    private String contentType;
    private String contentValue;

    public String getTitlePl() {
        return titlePl;
    }

    public void setTitlePl(String titlePl) {
        this.titlePl = titlePl;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getContentValue() {
        return contentValue;
    }

    public void setContentValue(String contentValue) {
        this.contentValue = contentValue;
    }
}
