package org.jive.service;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.apache.commons.lang3.SerializationUtils;
import org.jive.bean.ConfServeur;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import shared.CfgRapport;
import shared.SampleFreemarker;

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

	public Consumer createConsumer(Channel channel) {
		Consumer consumer = new DefaultConsumer(channel) {
			// traitement du consumer
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
					byte[] body) throws IOException {
				// Lit le message
				// String message = new String(body, "UTF-8");
				CfgRapport cfgRapport = (CfgRapport) SerializationUtils.deserialize(body);
				System.out.println(" [x] Received '" + cfgRapport + "'");
				// Execute une action
				SampleFreemarker sampleFreemarker = new SampleFreemarker();
				sampleFreemarker.run(cfgRapport);
			}
		};
		return consumer;
	}
}
