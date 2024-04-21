package co.istad.mobilebankingcstad.mail;

import co.istad.mobilebankingcstad.mail.dto.MailRequest;
import co.istad.mobilebankingcstad.mail.dto.MailResponse;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Slf4j
@Service
@AllArgsConstructor
public class MailServiceImpl implements MailService{
    private final JavaMailSender javaMailSender;
    private final TemplateEngine templateEngine;
    @Override
    public MailResponse sent(MailRequest mailRequest) {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        Context context = new Context();
        context.setVariable("header", mailRequest.subject());
        String template = templateEngine.process("demo", context);

        try {
            helper.setTo(mailRequest.to());
            helper.setSubject(mailRequest.subject());
            helper.setText(template,true);
            javaMailSender.send(message);

        }catch (Exception e){
            log.error("Email sent failed to {}", mailRequest.to());
            throw new RuntimeException("Email sent failed ");
        }
        javaMailSender.send(message);
        return new MailResponse();
    }
}
