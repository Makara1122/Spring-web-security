package co.istad.mobilebankingcstad.mail.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record MailRequest(
        @NotBlank(message = "Email required")
        String to,
        @NotBlank(message = "Subject required")
        String subject,
        @NotBlank(message = "text required")
        String text
) {
}
