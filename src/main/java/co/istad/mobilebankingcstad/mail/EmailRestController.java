package co.istad.mobilebankingcstad.mail;

import co.istad.mobilebankingcstad.mail.dto.MailRequest;
import co.istad.mobilebankingcstad.mail.dto.MailResponse;
import jakarta.mail.internet.MimeMessage;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/mails")
@RequiredArgsConstructor
public class EmailRestController {
    private final  MailService mailService;

    @PostMapping("/send")
    public MailResponse sentEmail(@Valid @RequestBody MailRequest mailRequest) {
       return mailService.sent(mailRequest);
    }
}
