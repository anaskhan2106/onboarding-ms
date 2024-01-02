package com.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import com.constant.ApplicationConfigurationConstants;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
@Slf4j
public class KafkaConsumerFactoryConfig {

    @Value( "${bootstrapServers}" )
    String bootstrapServers;

    @Value( "${topicName}" )
    String topicName;

    @Value( "${retryCount}" )
    String retryCount;

    @Value( "${group-id}")
    String groupId;
    
    @Bean
    public Map< String, Object > consumerConfigs() {
        Map< String, Object > configProps = new HashMap<>();
        configProps.put( ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
                         bootstrapServers );
        configProps.put( ConsumerConfig.GROUP_ID_CONFIG, groupId );
        configProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        configProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class.getName());
        configProps.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");
        configProps.put(
                ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS, JsonDeserializer.class.getName()
                 );
        configProps.put(
                ErrorHandlingDeserializer.KEY_DESERIALIZER_CLASS, StringDeserializer.class.getName()
                 );
    
        configProps.put( ApplicationConfigurationConstants.KEY_KAFKA_SECURITY_PROTOCOL, ApplicationConfigurationConstants.KEY_KAFKA_SSL_VALUE );
        configProps.put( ApplicationConfigurationConstants.KEY_KAFKA_SSL_TRUST_STORE_LOCATION, getTrustStoreFileLocation() );
        configProps.put( ApplicationConfigurationConstants.KEY_KAFKA_SSL_TRUST_STORE_CREDENTIALS, getTrustStoreFilePassword() );
        configProps.put( ApplicationConfigurationConstants.KEY_KAFKA_SSL_KEY_STORE_LOCATION, getKeyStoreFileLocation() );
        configProps.put( ApplicationConfigurationConstants.KEY_KAFKA_SSL_KEY_STORE_CREDENTIALS, getTrustStoreFilePassword() );
        configProps.put( ApplicationConfigurationConstants.KEY_KAFKA_SSL_KEY_CREDENTIALS, getTrustStoreFilePassword() );
        configProps.put( ApplicationConfigurationConstants.KEY_KAFKA_END_POINT_ALGORITHM, "" );
        return configProps;
    }
    
    @Bean
    public ConsumerFactory< String, String> consumerFactory() {
        ErrorHandlingDeserializer<String> errorHandlingDeserializer
                = new ErrorHandlingDeserializer<>(new JsonDeserializer<>(String.class));

        return new DefaultKafkaConsumerFactory<>( consumerConfigs(), new StringDeserializer(),
                errorHandlingDeserializer );
    }
    
    @Bean
    public ConcurrentKafkaListenerContainerFactory< String, String> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory< String, String> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory( consumerFactory() );
        return factory;
    }

    private String getTrustStoreFileLocation() {
        String truststoreFileName = ApplicationConfigurationConstants.KAFKA_TRUST_STORE;
        
        File file = new File( truststoreFileName);
        log.info("Truststore File Location: {}", truststoreFileName);
        try ( FileOutputStream fos = new FileOutputStream( file)) {
            String truststore = new String( Files.readAllBytes( Paths.get( ApplicationConfigurationConstants.KAFKA_BROKER_TRUST_STORE_CERT_FILE ) ));
            fos.write( Base64.getDecoder().decode( truststore ) );
        } catch (Exception e) {
            log.error(
                    "error writing file: {} | {} | {}",
                    e.getClass().getCanonicalName(),
                    e.getMessage(),
                    e.getCause());
            return "";
        }
        return truststoreFileName;
    }
    
    private String getKeyStoreFileLocation() {
        String keystoreFileName = ApplicationConfigurationConstants.KAFKA_KEY_STORE;
        
        File file = new File( keystoreFileName);
        log.info("Truststore File Location: {}", keystoreFileName);
        try ( FileOutputStream fos = new FileOutputStream( file)) {
            String keystore = new String(Files.readAllBytes(Paths.get(ApplicationConfigurationConstants.KAFKA_BROKER_KEY_STORE_CERT_FILE )));
            fos.write( Base64.getDecoder().decode( keystore ) );
        } catch (Exception e) {
            log.error(
                    "error writing file: {} | {} | {}",
                    e.getClass().getCanonicalName(),
                    e.getMessage(),
                    e.getCause());
            return "";
        }
        return keystoreFileName;
    }
    
    private Object getTrustStoreFilePassword() {
        String trustStorePassword = null;
        try {
            trustStorePassword = new String(Files.readAllBytes(Paths.get(ApplicationConfigurationConstants.TRUSTSTORE_PWD_FILE)));
        } catch ( IOException e) {
            log.error("Error reading truststore password" + e.getMessage());
        }
        return trustStorePassword;
    }
}
