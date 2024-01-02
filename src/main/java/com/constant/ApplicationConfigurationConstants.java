package com.constant;


public class ApplicationConfigurationConstants {
    
    public static final String KEY_KAFKA_SECURITY_PROTOCOL = "security.protocol";
    public static final String KEY_KAFKA_SSL_TRUST_STORE_LOCATION = "ssl.truststore.location";
    public static final String KEY_KAFKA_SSL_KEY_STORE_LOCATION = "ssl.keystore.location";
    public static final String KEY_KAFKA_SSL_TRUST_STORE_CREDENTIALS = "ssl.truststore.password";
    public static final String KEY_KAFKA_SSL_KEY_STORE_CREDENTIALS = "ssl.keystore.password";
    public static final String KEY_KAFKA_SSL_KEY_CREDENTIALS = "ssl.key.password";
    public static final String KEY_KAFKA_END_POINT_ALGORITHM = "ssl.endpoint.identification.algorithm";
    public static final String KAFKA_TRUST_STORE="/tmp/kafkaTrustStore.jks";
    public static final String KAFKA_KEY_STORE= "/tmp/kafkaKeyStore.jks";
    public static final String KAFKA_BROKER_TRUST_STORE_CERT_FILE = "/etc/secrets/base64EncodedKafkaBrokerTrustStoreCertificate.txt";
    public static final String KAFKA_BROKER_KEY_STORE_CERT_FILE = "/etc/secrets/base64EncodedKafkaBrokerKeyStoreCertificate.txt";
    public static final String TRUSTSTORE_PWD_FILE = "/etc/secrets/kafkaBrokerTrustStoreCredentials.txt";
    public static final String KEY_KAFKA_SSL_VALUE = "SSL";

    private ApplicationConfigurationConstants() { //
    }
}
