package pl.kasztany.orlenfirstaid.model;

public class TranscriptData {
    private String text;
    private String accidentId;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAccidentId() {
        return accidentId;
    }

    public void setAccidentId(String accidentId) {
        this.accidentId = accidentId;
    }

    public TranscriptData(String text, String accidentId) {
        this.text = text;
        this.accidentId = accidentId;
    }
}
