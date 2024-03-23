package com.algafood.algafoodapi.core.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.algafood.algafoodapi.domain.service.EnvioEmailService;
import com.algafood.algafoodapi.infrasTructure.service.email.FakeEnvioEmailService;
import com.algafood.algafoodapi.infrasTructure.service.email.SmtpEnvioEmailService;

@Configuration
public class EmailConfig {
    @Autowired
    private EmailProperties emailProperties;

    @Bean
    public EnvioEmailService envioEmailService() {

        switch (emailProperties.getImpl()) {
            case FAKE:
                return new FakeEnvioEmailService();
            case SMTP:
                return new SmtpEnvioEmailService();
            default:
                return null;
        }
    }
}
