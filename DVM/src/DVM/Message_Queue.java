package DVM;

import javax.jms.*;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.broker.BrokerFactory;
import org.apache.activemq.broker.BrokerService;

public class Message_Queue
{
	
		   Connection connection = connectionFactory.createConnection();
		   brokerService broker = broker;
		   try 
		   {
			   //message queue ���� �κ�
		      Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		      Queue queue = session.createQueue("MsgQueue");
		      
		      //Message ��ü ����
		      Message Message = new Message(ID, Type);
		      
		      //message ��ȯ�� ��ü ���� �� ť���� ����
		      MessageProducer producer = session.createProducer(queue);
			  producer.send((javax.jms.Message) Message);
			  
			  //message �޼��� ��ȯ �κ�
			  MessageConsumer consumer = session.createConsumer(queue);
		      connection.start();
		      
			   //��ü �޽��� ���� �κ�
			   Destination destination = createDestination(dest);
			   MessageProducer producer = session.createProducer(destination);
			   ObjectMessage message = session.createObjectMessage();
		      

		      //�޽��� ��� �м� �κ�
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

		   }
		   
		   public void dequeue()
		    {
		        Message msg = queue.poll();
		        switch(msg.Type)
		        {
		        	case 1:
		        		// ���Ȯ�ο�û ������ ���� 
		        	case 2:
		        		// ��� ���� boolean�� ���
		        	case 3:
		        		//  ������ ���� ���� // Ÿ��Ʋ+������ȣ
		        	case 4:
		        		// �ּ� ��û
		        	case 5:
		        		// �ּ� ����
		        	case 6:
		        		// ���� �Ǹ� Ȯ�� Ÿ��Ʋ
		        	case 7:
		        		//���� �Ǹ� ���� �Ҹ���
		        }
		    }
}