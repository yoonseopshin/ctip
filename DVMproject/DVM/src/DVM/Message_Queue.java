package DVM;

import java.io.Serializable;
import java.util.Properties;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.broker.BrokerFactory;
import org.apache.activemq.broker.BrokerService;

public class Message_Queue
{

    int ID;
    int Type;
    int Data;

    //1차시도 (브로커 연결 및 TCP) 브로커 설정 및 TCP포트 설정을 필요로 하며 이후 ID에 따른 경로지정 필요
		  /* try
		   {
			   //message queue 생성 부분
		      Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		      Queue queue = session.createQueue("MsgQueue");

		      //컨트롤러부분에서 ID,TYPE넘겨주기.

		      //Message 객체 생성
		      Message Message = new Message(ID, Type);

		      //message 변환자 객체 생성 및 큐로의 전송
		      MessageProducer producer = session.createProducer(queue);
			  producer.send((javax.jms.Message) Message);

			  //message 메세지 반환 부분
			  MessageConsumer consumer = session.createConsumer(queue);
		      connection.start();

			   //객체 메시지 생성 부분
			   Destination destination = createDestination(dest);
			   MessageProducer producer = session.createProducer(destination);
			   ObjectMessage message = session.createObjectMessage();


		      //메시지 헤더 분석 부분
		      if (contentType != null)
		      {
		         message.setStringProperty(HttpHeaderProperty.CONTENT_TYPE, contentType);
		      }
		      message.setObject(object);

		      producer.send(message);
		   }
		   finally
		   {
		      conn.close();
		   }

		   void MsgConstruct(int ID, int Type, int intData, boolean booldata)
		   {

			   Message msg = new Message(ID,Type);
			   if(intData != -1)
				   msg.intInput(intData);
			   else
				   msg.boolInput(booldata);

		   }*/

    //2차 시도 웹 로직 연결을 통한 메세지 큐 전송 - 아이디에 따라 어떻게 경로 지정하는지 이해못함.
    //MQ생성

    Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
    Queue queue = session.createQueue("MsgQueue");

    public void MsgSend(int ID, int Type, int Data)
    {
        try
        {
            Properties h = new Properties();
            h.put(Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory");
            h.put(Context.PROVIDER_URL, "t3://localhost:7001");

            Context ctx = new InitialContext(h);

            // 1.Connection
            QueueConnectionFactory factory = (QueueConnectionFactory)ctx.lookup("weblogic.jms.ConnectionFactory");

            // 2. Queue 찾기
            Queue queue = (Queue)ctx.lookup("MsgQueue");

            // 3. 커넥션 연결
            QueueConnection con = factory.createQueueConnection();

            // 4. 세션 만들기
            QueueSession session = con.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);

            // 5/ sender 만들기
            QueueSender qsender = session.createSender(queue);

            //6. message 만들기
            Destination destination = createDestination(); // 전송 목표지정
            MessageProducer producer = session.createProducer(destination);
            ObjectMessage message = session.createObjectMessage();


            // message 내용 지정
            Message msg = new Message(ID,Type);
            msg.intInput(Data);
            message.setObject((Serializable) msg);

            // message 보내기
            qsender.send(message);

            //빈 내용 보내서 메시지 끝을 알린다.
            qsender.send(session.createMessage());
            con.close();
        }
        catch ( Exception e)
        {
            System.out.println(e);
        }
    }


}

    // 스위치부분은 메세지큐가 완성되야 가능할듯
    public void dequeue()
    {
        Message msg = queue.poll();
        switch(msg.Type)
        {
            case 1:
                // 재고확인요청 시퀀스 시작
            case 2:
                // 재고 응답 boolean값 출력
            case 3:
                //  선결제 정보 전송 // 타이틀+인증번호
            case 4:
                // 주소 요청
            case 5:
                // 주소 응답
            case 6:
                // 음료 판매 확인 타이틀
            case 7:
                //음료 판매 응답 불리안
        }
    }