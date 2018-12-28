package app;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import com.thoughtworks.xstream.XStream;


public class UserPublisher {
    private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;
    private static String subject = "queue/demo";
    
    protected static void sendPerson(String username, String password) throws JMSException {
        ConnectionFactory connectionFactory =
                new ActiveMQConnectionFactory(url);
        Connection connection = connectionFactory.createConnection();
        connection.start();
        
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
       	System.out.println("Sending new user: " +username);
        Destination destination = session.createQueue(subject);
        MessageProducer producer = session.createProducer(destination);
        
    	Person p = new Person (username, password);
    	XStream xstream = new XStream();
    	String person_xml = xstream.toXML(p);
    	
        TextMessage msg = session.createTextMessage(person_xml);
        producer.send(msg);
        System.out.println("Sent message '" + msg.getText() + "'");
    }
}
