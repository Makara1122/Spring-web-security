package co.istad.mobilebankingcstad.mail;

import co.istad.mobilebankingcstad.mail.dto.MailRequest;
import co.istad.mobilebankingcstad.mail.dto.MailResponse;

public interface MailService {
    MailResponse sent(MailRequest mailRequest);
}
