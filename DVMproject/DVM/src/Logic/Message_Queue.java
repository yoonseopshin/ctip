package Logic;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Message_Queue
{
    private static final String EXCHANGE_NAME = "topic_logs";

    ConnectionFactory factory = new ConnectionFactory();
    Connection connection = null;
    Channel channel = null;

    private int ID = -1;
    private int Type = -1;
    private int Data = -1;
    private int thisDVMID = -1;

    public void initDVMID(int id)
    {
        this.thisDVMID = id;
    }


    public void MsgSend(int id, int type, int data)
    {
        //연결 시작
        factory.setHost("localhost");
        factory.setPort(15672);
        factory.setUsername("hello");
        factory.setPassword("hello");
        //큐 생성
        try
        {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("localhost");
            connection = factory.newConnection();
            channel = connection.createChannel();

            channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC);

            String message = getMessage(ID, Type, Data);
            String routingKey = getRouting(ID);


            channel.basicPublish(EXCHANGE_NAME, routingKey, null, message.getBytes("UTF-8"));
            System.out.println("[x] Sent '" + routingKey + "':'" + message + "'");
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (channel != null)
            {
                try
                {
                    channel.close();
                } catch (Exception ignore) { }
            }

            if (connection != null)
            {
                try
                {
                    connection.close();
                } catch (Exception ignore) { }
            }
        }
    }

    private static String getMessage(int id, int type, int data)
    {
        String msg = id+" "+ type +" "+data;

        return msg;
    }

    private  static String getRouting(int id)
    {
        if(id == -1)
        {
            System.out.println("Target DVM ID is Unvailed");
            return "anonymous.info";
        }
        String key = "DVM"+id;
        return key;
    }

    public String msgRecieve()throws Exception
    {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC);
        String queueName = channel.queueDeclare().getQueue();
        String key[] = {"thisDVMID"};
        for(String bindingKey : key)
        {
            channel.queueBind(queueName,EXCHANGE_NAME,bindingKey);
        }

        System.out.println("[*] Waiting for message . To exit press Ctrl + C");

        Consumer consumer = new DefaultConsumer(channel)
        {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body,"UTF-8");
                System.out.println(" [x] Received ' " + envelope.getRoutingKey() + "':'"+message+"'");
            }
        };
        channel.basicConsume(queueName,true,consumer);
        return "임시로 넣어논 리턴값";
    }

   /* //메세지 생성 및 전송
    public void producer(int ID, int Type, int data)
    {
        //어플리케이션간의 커넥션을 생성하고 브로커와 어플리케이션간의 TCP연결을 시도함.

        //데이터 통로 연결
        try (Connection connection = factory.newConnection(); Channel channel = connection.createChannel())
        {
            do
            {
                //채널에 큐 생성
                channel.exchangeDeclare(EXCHANGE_NAME, "topic");

                String routingKey = getRouting(this.ID);
                String message = getMessage(Data);

                channel.exchangeDeclare(EXCHANGE_NAME, routingKey, null, message.getBytes("UTF-8"));

                channel.queueDeclare(QUEUE_NAME, false, false, false, null);
                String message = ID+" "+Type+" "+data;
                //메세지 전송
                channel.basicPublish("", QUEUE_NAME,null, message.getBytes());
                Thread.sleep(10);
            }while(ID == 0);
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
    }*/
}
