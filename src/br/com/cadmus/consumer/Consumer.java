package br.com.cadmus.consumer;

import javax.jms.*;
import javax.naming.InitialContext;
import java.util.Scanner;

public class Consumer {

    public static void main(String[] args) throws Exception{
        InitialContext context = new InitialContext();
        ConnectionFactory cf = (ConnectionFactory)context.lookup("ConnectionFactory");
        Connection conexao = cf.createConnection();
        conexao.start();
        Session session = conexao.createSession(false, 1);
        Destination fila = (Destination)context.lookup("pedidos");
        MessageConsumer consumer = session.createConsumer(fila);
        consumer.setMessageListener(new MessageListener() {
            public void onMessage(Message message) {
                TextMessage textMessage = (TextMessage)message;

                try {
                    System.out.println(textMessage.getText());
                } catch (JMSException var4) {
                    var4.printStackTrace();
                }

            }
        });
        (new Scanner(System.in)).nextLine();
        session.close();
        conexao.close();
        context.close();
    }
}
