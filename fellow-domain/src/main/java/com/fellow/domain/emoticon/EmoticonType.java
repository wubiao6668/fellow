package com.fellow.domain.emoticon;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wubiao on 2016/9/21.
 */
public class EmoticonType implements Serializable {

    private static final long serialVersionUID = -3431485986990308698L;
    private String typ;
    private String nm;
    private List<EmoticonItem> items;

    public String getTyp() {
        return typ;
    }

    public void setTyp(String typ) {
        this.typ = typ;
    }

    public String getNm() {
        return nm;
    }

    public void setNm(String nm) {
        this.nm = nm;
    }

    public List<EmoticonItem> getItems() {
        return items;
    }

    public void setItems(List<EmoticonItem> items) {
        this.items = items;
    }
}
