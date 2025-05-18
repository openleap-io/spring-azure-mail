package com.openleap.io.springazuremail.service;

import com.microsoft.graph.models.Message;
import com.microsoft.graph.models.UserSendMailParameterSet;
import com.microsoft.graph.requests.GraphServiceClient;
import com.openleap.io.springazuremail.utl.EmailUtils;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    private final GraphServiceClient graphServiceClient;

    public EmailService(GraphServiceClient graphServiceClient) {
        this.graphServiceClient = graphServiceClient;
    }

    public void sendEmail(String sender, String recipientList, String subject, String body) {
        try {
            Message emailMessage = createEmailMessage(recipientList, subject, body);
            sendEmailViaGraph(sender, emailMessage);
        } catch (Exception error) {
            System.out.println("Error sending email: " + error.getMessage());
            error.printStackTrace();
        }
    }

    private Message createEmailMessage(String recipientList, String subject, String body) {
        Message message = EmailUtils.createMessage(subject, body);
        EmailUtils.addRecipientsToMessage(recipientList, message);
        return message;
    }

    private void sendEmailViaGraph(String sender, Message message) {
        graphServiceClient.users(sender)
                .sendMail(UserSendMailParameterSet
                        .newBuilder()
                        .withMessage(message)
                        .withSaveToSentItems(true)
                        .build())
                .buildRequest()
                .post();
    }
}
