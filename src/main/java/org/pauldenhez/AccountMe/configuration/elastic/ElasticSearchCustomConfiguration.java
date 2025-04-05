package org.pauldenhez.accountme.configuration.elastic;

import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ssl.SslBundle;
import org.springframework.boot.ssl.SslBundles;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchConfiguration;

import javax.net.ssl.SSLContext;
import java.io.InputStream;
import java.util.Base64;

@Configuration
public class ElasticSearchCustomConfiguration extends ElasticsearchConfiguration {

    @Value("${elasticsearch.fingerprint.base64}")
    private String fingerprintBase64;
    @Value("${elasticsearch.user}")
    private String elasticUser;
    @Value("${elasticsearch.password}")
    private String elasticPassword;

    private final SslBundles sslBundles;

    public ElasticSearchCustomConfiguration(SslBundles sslBundles) {
        this.sslBundles = sslBundles;
    }

    @Override
    public ClientConfiguration clientConfiguration() {
        final String fingerprint = new String(Base64.getDecoder().decode(fingerprintBase64));
        InputStream certInputStream = getClass().getClassLoader().getResourceAsStream("certs/http_ca.crt");
        if(certInputStream == null) {
            throw new IllegalStateException();
        }
        SslBundle sslBundle = sslBundles.getBundle("elastic");
        SSLContext sslContext = sslBundle.createSslContext();

        return ClientConfiguration.builder()
                .connectedTo("localhost:9200")
                .usingSsl(sslContext)
                .withBasicAuth(elasticUser, elasticPassword)
                .build();
    }
}
