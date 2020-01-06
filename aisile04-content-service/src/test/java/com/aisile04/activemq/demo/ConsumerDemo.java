package com.aisile04.activemq.demo;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnectionFactory;

public class ConsumerDemo {

	public static void main(String[] args) throws Exception {
		//1.创建连接工厂
				ConnectionFactory connectionFactory=new ActiveMQConnectionFactory("tcp://localhost:61616");
				//2.获取连接
				Connection connection = connectionFactory.createConnection();
				//3.启动连接
				connection.start();
				//4.获取session  (参数1：是否启动事务,参数2：消息确认模式)
				Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
				//5.创建队列对象
				//Queue queue = session.createQueue("test-queue");
				Topic topic = session.createTopic("test-topic");
				//6.创建消息消费
				MessageConsumer consumer = session.createConsumer(topic);
				//7.监听消息
				consumer.setMessageListener(new MessageListener() {
					public void onMessage(Message message) {
						TextMessage textMessage=(TextMessage)message;
						try {
							System.out.println("2接收到消息："+textMessage.getText());
						} catch (JMSException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				});

				/**
				 * 这个东西只能在demo中写
				 */
				//8.等待键盘输入
				System.in.read();	
				//9.关闭资源
				consumer.close();
				session.close();
				connection.close();

	}
}
