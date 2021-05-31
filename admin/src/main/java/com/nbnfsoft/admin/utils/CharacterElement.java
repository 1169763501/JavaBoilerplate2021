package com.nbnfsoft.admin.utils;

public class CharacterElement {
    private int unicode;

    private String pinyin;

    private String wubi;

    public CharacterElement() {}

    public CharacterElement(String str) {
        if (str != null) {
            String[] content = str.split(",");
            if (content.length == 3) {
                try {
                    this.unicode = Integer.parseInt(content[0]);
                } catch (Exception e) {
                    System.out.println("CharacterElement: " + e.getMessage());
                }
                this.pinyin = content[1];
                this.wubi = content[2].split("[\\|]")[0];
            }
        }
    }

    public int getUnicode() {
        return unicode;
    }

    public void setUnicode(int unicode) {
        this.unicode = unicode;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public String getWubi() {
        return wubi;
    }

    public void setWubi(String wubi) {
        this.wubi = wubi;
    }

}