package com.algafood.algafoodapi.infrasTructure.service.email;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.algafood.algafoodapi.core.email.EmailProperties;
import com.algafood.algafoodapi.domain.service.EnvioEmailService;

@Service
public class SmtpEnvioEmailService implements EnvioEmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private EmailProperties emailProperties;

    @Override
    public void envio(Mensagem msn) {
        try {
            MimeMessage mineMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mineMessage, "UTF-8");
            helper.setFrom(emailProperties.getRemetente());
            helper.setTo(msn.getDestinatarios().toArray(new String[0]));
            helper.setSubject(msn.getAssunto());
            helper.setText(msn.getCorpo(), true);

            mailSender.send(mineMessage);

        } catch (Exception e) {
            throw new EmailException("Não foi possível enviar e-mail", e);
        }

    }

}
