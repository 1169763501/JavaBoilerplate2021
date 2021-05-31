package com.nbnfsoft.admin.event;

import com.nbnfsoft.admin.utils.FriendlyException;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/***
 * 定义监听
 */
@Component
public class MsgEventHandler {

    //@EventListener
    //public void handleEvent(MsgEvent event) {
    //    System.out.println("线程id：" + Thread.currentThread().getId() + " 同步调用，消息内容：" + event.content);
    //    throw new FriendlyException("不要了");
    //}

    @Async
    @EventListener
    public void handleEvent2(MsgEvent event) {
        System.out.println("线程id：" + Thread.currentThread().getId() + " 异步调用，消息内容：" + event.content);
        throw new FriendlyException("不要了");
    }
}
