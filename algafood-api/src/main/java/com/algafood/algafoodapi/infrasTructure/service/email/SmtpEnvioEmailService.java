package com.algafood.algafoodapi.infrasTructure.service.email;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.algafood.algafoodapi.core.email.EmailProperties;
import com.algafood.algafoodapi.domain.service.EnvioEmailService;

import freemarker.template.Configuration;

import freemarker.template.Template;

public class SmtpEnvioEmailService implements EnvioEmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private EmailProperties emailProperties;

    @Autowired
    private ProcessadoEmailTemplate procesadorEmailTemplate;

    @Override
    public void envio(Mensagem msn) {
        try {
            MimeMessage mineMessage = criarMimeMessage(msn);
            mailSender.send(mineMessage);

        } catch (Exception e) {
            throw new EmailException("Não foi possível enviar e-mail", e);
        }

    }

    protected MimeMessage criarMimeMessage(Mensagem msn) throws MessagingException {
        String corpo = procesadorEmailTemplate.processarTemplate(msn);
        MimeMessage mineMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mineMessage, "UTF-8");
        helper.setFrom(emailProperties.getRemetente());
        helper.setTo(msn.getDestinatarios().toArray(new String[0]));
        helper.setSubject(msn.getAssunto());
        helper.setText(corpo, true);
        return mineMessage;
    }

}
