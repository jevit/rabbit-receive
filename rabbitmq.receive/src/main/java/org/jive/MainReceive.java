package org.jive;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.apache.commons.lang3.SerializationUtils;
import org.jive.bean.ConfServeur;
import org.jive.service.MessageService;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import freemarkers.CfgRapport;
import freemarkers.SampleFreemarker;

public class MainReceive {
	private final static String QUEUE_NAME = "test";

	private static MessageService messagerService = new MessageService();

	private final static String SERVER_ADRESSE = "localhost";
	private final static String SERVER_USERNAME = "admin";
	private final static String SERVER_PASSWORD = "admin";

	/**
	 * Recuperer un message
	 * 
	 * @param argv
	 * @throws TimeoutException
	 * @throws IOException
	 */
	public static void main(String[] argv) throws TimeoutException, IOException {
		// TODO utiliser un parseur d'arguments sur le programme
		// initArgs..
		ConfServeur confServeur = new ConfServeur(SERVER_ADRESSE, SERVER_USERNAME, SERVER_PASSWORD);

		// Création de la connexion
		Connection connection = messagerService.createConnection(confServeur);

		// Connexion à un canal
		Channel channel = messagerService.getChannel(connection, QUEUE_NAME);

		// Affiche un message d'attente
		System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

		// Instancie un consommateur
		Consumer consumer = new DefaultConsumer(channel) {
			// traitement du consommateur
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
					byte[] body) throws IOException {
				// String message = new String(body, "UTF-8");
				CfgRapport cfgRapport = (CfgRapport) SerializationUtils.deserialize(body);
				System.out.println(" [x] Received '" + cfgRapport + "'");
				SampleFreemarker sampleFreemarker = new SampleFreemarker();
				sampleFreemarker.run(cfgRapport);
			}
		};

		// Ecoute du canal via le consommateur
		channel.basicConsume(QUEUE_NAME, true, consumer);
	}
}
