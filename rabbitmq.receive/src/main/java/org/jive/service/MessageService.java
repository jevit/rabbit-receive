package org.jive.service;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.jive.bean.ConfServeur;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class MessageService {

	/**
	 * Création d'une connexion
	 * 
	 * @return
	 * @throws IOException
	 * @throws TimeoutException
	 */
	public Connection createConnection(ConfServeur configurationServeur) throws IOException, TimeoutException {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost(configurationServeur.getServeurAdresse());
		factory.setUsername(configurationServeur.getServeurUtilisateur());
		factory.setPassword(configurationServeur.getServeurPassword());
		Connection connection = factory.newConnection();
		return connection;
	}

	/**
	 * Connexion à un canal
	 * 
	 * @param connection
	 *            connexion active
	 * @param queueName
	 *            nom de la file d'attente
	 * @return
	 * @throws IOException
	 */
	public Channel getChannel(Connection connection, String queueName) throws IOException {
		Channel channel = connection.createChannel();
		channel.queueDeclare(queueName, true, false, false, null);
		return channel;
	}

	public Consumer createConsumer() {

	}
}
