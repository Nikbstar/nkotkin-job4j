package ru.nik66.email;

import org.junit.Test;

import java.util.concurrent.RejectedExecutionException;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class EmailNotificationTest {

    @Test
    public void emailTo() throws InterruptedException {
        User user = new User("Nikolay", "e@mail.com");
        EmailForTest emailNotification = new EmailForTest();
        emailNotification.emailTo(user);
        Thread.sleep(100);
        String actual = emailNotification.getSend();
        String excepted = "Notification Nikolay to email e@mail.com";
        assertThat(actual, is(excepted));
    }

    @Test (expected = RejectedExecutionException.class)
    public void close() {
        EmailNotification emailNotification = new EmailNotification();
        emailNotification.close();
        emailNotification.emailTo(new User("Nikolay", "e@mail.com"));
    }
}

class EmailForTest extends EmailNotification {

    private String send;

    @Override
    public void send(String subject, String body, String email) {
        this.send = subject;
    }

    public String getSend() {
        return send;
    }
}