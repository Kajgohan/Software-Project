package bishopfish.utils.twillotexting;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class SendTwilloText {
    // Find your Account Sid and Token at twilio.com/user/account
    public static final String ACCOUNT_SID = "AC5c07db3fc5cf09224c77be4fb6104e8d";
    public static final String AUTH_TOKEN = "5219ce2bca18d7b0a16a22385bd44fa4";

    public static void sendText(String toSend) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message.creator(new PhoneNumber("+15167545284"), new PhoneNumber("+15167277148"), toSend).create();
      // System.out.println(message.getSid());
    }
}
