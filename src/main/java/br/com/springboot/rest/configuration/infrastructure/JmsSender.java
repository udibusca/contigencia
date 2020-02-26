package br.com.springboot.rest.configuration.infrastructure;

import javax.jms.JMSException;
import javax.jms.Message;

import org.springframework.jms.JmsException;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessagePostProcessor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class JmsSender {

	private JmsTemplate jmsTemplate;
	
	public JmsSender(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}
	
	public <T> void send(String destination, T payload) {
		try {
			jmsTemplate.convertAndSend(destination, payload, new MessagePostProcessor() {
				
				@Override
				public Message postProcessMessage(Message message) throws JMSException {
					return message;
				}
			});
		} catch (JmsException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
}
