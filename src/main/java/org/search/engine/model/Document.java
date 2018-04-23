package org.search.engine.model;

public class Document {

    public Document(){};

    public Document(Long id, String text){
        this.id = id;
        this.text = text;
    }

    private Long id;

    private String text;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Document document = (Document) o;

        if (!id.equals(document.id)) return false;
        if (!text.equals(document.text)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + text.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Document{" +
                "id=" + id +
                ", text='" + text + '\'' +
                '}';
    }
}
