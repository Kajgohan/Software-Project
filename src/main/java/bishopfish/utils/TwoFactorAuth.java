package bishopfish.utils;


import bishopfish.utils.twillotexting.SendTwilloText;

import java.util.Random;
import java.util.Scanner;

public class TwoFactorAuth {
    private int randAuth = ((int) (Math.random() * 1000000.0));
    private Integer randomHold = (Integer) randAuth;
    private String random = String.format("%06d", randomHold);

    public void sendVerification(){
        random = String.format("%06d", randomHold);
        SendTwilloText.sendText((("Your verification code is: ") + random));
        System.out.println("Key: " + random);

    }

    public boolean verifyLogin(String input){
        if (input.equals(random)){
            randomHold = ((int) (Math.random() * 1000000.0));
            random = String.format("%06d", randomHold);

            return true;
        }
        else return false;
    }

    public static void main(String args[]){
        TwoFactorAuth myAuth = new TwoFactorAuth();
        myAuth.sendVerification();
        Scanner keyboard = new Scanner(System.in);
        //String verify = keyboard.nextLine();
        //System.out.println(myAuth.verifyLogin(verify));
    }
}