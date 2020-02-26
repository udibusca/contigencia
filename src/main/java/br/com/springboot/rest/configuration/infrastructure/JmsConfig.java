package br.com.springboot.rest.configuration.infrastructure;

import javax.jms.ConnectionFactory;
import javax.jms.ExceptionListener;
import javax.jms.JMSException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.annotation.JmsListenerConfigurer;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerEndpointRegistrar;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.util.ErrorHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.mq.jms.MQConnectionFactory;
import com.ibm.msg.client.wmq.WMQConstants;

@EnableJms
@Configuration
public class JmsConfig implements JmsListenerConfigurer{

	@Bean
	@Primary
	public MQConnectionFactory mqConnectionFactory() throws JMSException {
		MQConnectionFactory cf = new MQConnectionFactory();
		cf.setStringProperty(WMQConstants.WMQ_QUEUE_MANAGER, "QM1");
		cf.setStringProperty(WMQConstants.WMQ_HOST_NAME, "localhost");
		cf.setStringProperty(WMQConstants.WMQ_PORT, "1414");
		cf.setStringProperty(WMQConstants.WMQ_CHANNEL, "DEV.ADMIN.SVRCONN");
		cf.setIntProperty(WMQConstants.WMQ_CONNECTION_MODE, WMQConstants.WMQ_CM_CLIENT);
		cf.setStringProperty(WMQConstants.USERID, "admin");
		cf.setStringProperty(WMQConstants.PASSWORD, "passw0rd");
		cf.setBooleanProperty(WMQConstants.USER_AUTHENTICATION_MQCSP, true);
		
		return cf;
	}
	
	@Bean("CachingConnectionFactory")
	public CachingConnectionFactory cachingConnectionFactory(MQConnectionFactory connectionFactory) {
		CachingConnectionFactory cache = new CachingConnectionFactory((MQConnectionFactory)connectionFactory);
		return cache;
	}
	
	
	
	@Bean//(name="ContainerFactory")
	public DefaultJmsListenerContainerFactory queueContainerFactory( ConnectionFactory cf) {
		CustomJmsListenerContainerFactory factory = new CustomJmsListenerContainerFactory();
		factory.setConnectionFactory(cf);
		/*
		 * factory.setErrorHandler(jeh); factory.setExceptionListener(jeh);
		 */
		factory.setSessionTransacted(true);
		
		return factory;
	}
	
	@Bean
	public JmsExceptionHandler jmsExceptionHandler() {
		return new JmsExceptionHandler();
	}
	
	@Bean
	public MessageConverter messageConverter(ObjectMapper mapper) {
		MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
		converter.setTargetType(MessageType.TEXT);
		converter.setTypeIdPropertyName("_type");
		converter.setObjectMapper(mapper);
		return converter;
	}
	
	@Bean
	public JmsTemplate jmsTemplate( CachingConnectionFactory factory, MessageConverter converter) {
		JmsTemplate template =  new JmsTemplate(factory);
		template.setMessageConverter(converter);
		return template;
	}
	
	public static class CustomJmsListenerContainerFactory extends DefaultJmsListenerContainerFactory{
		private ExceptionListener exceptionListener;
		
		public void setExceptionListener(ExceptionListener exception) {
			this.exceptionListener = exception;
		}
		
		@Override
		protected DefaultMessageListenerContainer createContainerInstance() {
			DefaultMessageListenerContainer container = new DefaultMessageListenerContainer();
			container.setExceptionListener(exceptionListener);
			return container;
		}
	}
	
	public class JmsExceptionHandler implements ErrorHandler, ExceptionListener{

		@Override
		public void onException(JMSException exception) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void handleError(Throwable t) {
			// TODO Auto-generated method stub
			
		}
		
	}

	/* (non-Javadoc)
	 * @see org.springframework.jms.annotation.JmsListenerConfigurer#configureJmsListeners(org.springframework.jms.config.JmsListenerEndpointRegistrar)
	 */
	@Override
	public void configureJmsListeners(JmsListenerEndpointRegistrar registrar) {
		// TODO Auto-generated method stub
		
	}
}
