package com.openleap.io.springazuremail.config;

import com.azure.identity.ClientSecretCredential;
import com.azure.identity.ClientSecretCredentialBuilder;
import com.microsoft.graph.authentication.TokenCredentialAuthProvider;
import com.microsoft.graph.requests.GraphServiceClient;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@EnableConfigurationProperties(ClientProperties.class)
public class Config {

    private final ClientProperties clientProperties;

    public Config(ClientProperties clientProperties) {
        this.clientProperties = clientProperties;
    }

    @Bean
    public GraphServiceClient graphServiceClient() {
        try {
            final ClientSecretCredential clientSecretCredential = new ClientSecretCredentialBuilder()
                    .clientId(clientProperties.getClientId())
                    .clientSecret(clientProperties.getClientSecret())
                    .tenantId(clientProperties.getTenantId())
                    .build();
            final TokenCredentialAuthProvider tokenCredentialAuthProvider = new TokenCredentialAuthProvider(
                    List.of(clientProperties.getScope()),
                    clientSecretCredential
            );
            return GraphServiceClient.builder()
                    .authenticationProvider(tokenCredentialAuthProvider)
                    .buildClient();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
