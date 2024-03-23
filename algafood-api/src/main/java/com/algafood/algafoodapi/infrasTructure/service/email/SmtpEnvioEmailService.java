package com.algafood.algafoodapi.infrasTructure.service.email;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.algafood.algafoodapi.core.email.EmailProperties;
import com.algafood.algafoodapi.domain.service.EnvioEmailService;

import freemarker.template.Configuration;

import freemarker.template.Template;

@Service
public class SmtpEnvioEmailService implements EnvioEmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private EmailProperties emailProperties;
    @Autowired
    private Configuration freemarkerConfig;

    @Override
    public void envio(Mensagem msn) {
        try {
            String corpo = processarTemplate(msn);
            MimeMessage mineMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mineMessage, "UTF-8");
            helper.setFrom(emailProperties.getRemetente());
            helper.setTo(msn.getDestinatarios().toArray(new String[0]));
            helper.setSubject(msn.getAssunto());
            helper.setText(corpo, true);
            mailSender.send(mineMessage);

        } catch (Exception e) {
            throw new EmailException("Não foi possível enviar e-mail", e);
        }

    }

    protected String processarTemplate(Mensagem mensagem) {
        try {
            Template template = freemarkerConfig.getTemplate(mensagem.getCorpo());
            return FreeMarkerTemplateUtils.processTemplateIntoString(template, mensagem.getVariaveis());
        } catch (Exception e) {
            throw new EmailException("Não foi possível montar o template do e-mail", e);
        }

    }
}
