package com.hs.entity;

/**
 * @author Hs
 * @Date 2022/6/19 0:33
 */
public class Note {
    private Integer fontSize;
    private Integer id;
    private Integer noteSizeW;
    private Integer noteSizeH;
    private String noteType;
    private Integer order;
    private Float positionX;
    private Float positionY;
    private String text;
    private Integer votes;

    public Integer getFontSize() {
        return fontSize;
    }

    public void setFontSize(Integer fontSize) {
        this.fontSize = fontSize;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNoteSizeW() {
        return noteSizeW;
    }

    public void setNoteSizeW(Integer noteSizeW) {
        this.noteSizeW = noteSizeW;
    }

    public Integer getNoteSizeH() {
        return noteSizeH;
    }

    public void setNoteSizeH(Integer noteSizeH) {
        this.noteSizeH = noteSizeH;
    }

    public String getNoteType() {
        return noteType;
    }

    public void setNoteType(String noteType) {
        this.noteType = noteType;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Float getPositionX() {
        return positionX;
    }

    public void setPositionX(Float positionX) {
        this.positionX = positionX;
    }

    public Float getPositionY() {
        return positionY;
    }

    public void setPositionY(Float positionY) {
        this.positionY = positionY;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getVotes() {
        return votes;
    }

    public void setVotes(Integer votes) {
        this.votes = votes;
    }

    @Override
    public String toString() {
        return "Note{" +
                "fontSize=" + fontSize +
                ", id=" + id +
                ", noteSizeW=" + noteSizeW +
                ", noteSizeH=" + noteSizeH +
                ", noteType='" + noteType + '\'' +
                ", order=" + order +
                ", positionX=" + positionX +
                ", positionY=" + positionY +
                ", text='" + text + '\'' +
                ", votes=" + votes +
                '}';
    }
}
