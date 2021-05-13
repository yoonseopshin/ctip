package Logic;

import jdk.tools.jlink.internal.packager.AppRuntimeImageBuilder;
import org.junit.Test;

import java.security.PrivateKey;

import static org.junit.Assert.*;

public class Message_QueueTest {
    private Message message;
    private Message_Queue message_queue;

    @Test
    public void sendMsg() {
        message = new Message(1);
        message.setmsg(1, 1, 1);

    }

    @Test
    public void dequeue() {
    }
}