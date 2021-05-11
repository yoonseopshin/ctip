package Logic;
import java.util.*;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Message_Queue
{
    private final static String QUEUE_NAME = "DVM_Network";

    //메세지 생성 및 전송
    public void producer(int ID, int Type, int data)
    {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        factory.setPort(15672);
        factory.setUsername("hello");
        factory.setPassword("hello");
        //어플리케이션간의 커넥션을 생성하고 브로커와 어플리케이션간의 TCP연결을 시도함.
        //channerl == 데이터 통로
        try (Connection connection = factory.newConnection(); Channel channel = connection.createChannel())
        {
            for (int i = 0; i <= 100000; i++)
            {
                //채널에 큐 생성
                channel.queueDeclare(QUEUE_NAME, false, false, false, null);
                String message = "Hello World!" + (int) (Math.random() * 100);
                //메세지 전송
                channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
                System.out.println(" [x] Set '" + message + "'");
                Thread.sleep(10);
            }
        }
        catch (TimeoutException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    public void consumer()
            throws Exception
    {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        factory.setPort(15672);
        factory.setUsername("hello");
        factory.setPassword("hello");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        //외부 데이터 반환
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println(" [x] Received '" + message + "'");
        };
        //종료되지않고 계속 리스닝을통해 데이터를 받게하는 코드
        channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> { });
    }
}
