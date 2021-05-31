package com.nbnfsoft.admin.event;

import org.springframework.context.ApplicationEvent;

/***
 * 定义事件
 */
public class MsgEvent extends ApplicationEvent {

    public String content;

    public MsgEvent(Object source, String content ) {
        super(source);
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
