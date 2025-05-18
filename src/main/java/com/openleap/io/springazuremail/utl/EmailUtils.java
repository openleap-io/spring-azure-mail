package com.openleap.io.springazuremail.utl;

import com.microsoft.graph.models.*;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class EmailUtils {
    public static void addRecipientsToMessage(String commaSeparatedRecipients, Message message) {
        List<Recipient> recipients = new ArrayList<>();
        String[] emailAddresses = commaSeparatedRecipients.split(",");
        for (String email : emailAddresses) {
            Recipient recipient = createRecipient(email.trim());
            recipients.add(recipient);
        }
        message.toRecipients = recipients;
    }

    private static Recipient createRecipient(String email) {
        Recipient recipient = new Recipient();
        EmailAddress emailAddress = new EmailAddress();
        emailAddress.address = email;
        recipient.emailAddress = emailAddress;
        return recipient;
    }

    @NotNull
    public static Message createMessage(String subject, String content) {
        Message message = new Message();
        message.subject = subject;
        message.body = createMessageBody(content);
        return message;
    }

    private static ItemBody createMessageBody(String content) {
        ItemBody body = new ItemBody();
        body.contentType = BodyType.TEXT;
        body.content = content;
        return body;
    }

}
