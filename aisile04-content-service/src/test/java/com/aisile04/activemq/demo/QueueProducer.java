package com.aisile04.activemq.demo;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnectionFactory;


public class QueueProducer {

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
		//6.创建消息生产者
		MessageProducer producer = session.createProducer(topic);
		//7.创建消息
		TextMessage textMessage = session.createTextMessage("欢迎来到神奇的爱思乐世界");
		//8.发送消息
		producer.send(textMessage);
		//9.关闭资源
		producer.close();
		session.close();
		connection.close();

	}
}
