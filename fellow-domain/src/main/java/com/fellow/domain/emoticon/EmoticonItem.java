package com.fellow.domain.emoticon;

import java.io.Serializable;

/**
 * Created by wubiao on 2016/9/21.
 */
public class EmoticonItem implements Serializable {

    private static final long serialVersionUID = 5861226631294037789L;
    private String name;
    private String src;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }
}
