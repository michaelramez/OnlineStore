/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.onlinestore.twilio;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Call;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.twiml.VoiceResponse;
import com.twilio.twiml.voice.Say;
import com.twilio.type.PhoneNumber;
import java.util.Random;

/**
 *
 * @author Michael Ramez
 */
public class TwilioService {

    private static final TwilioService twilioService = new TwilioService();
    private final String ACCOUNT_SID = "ACa28f4ca4bf8073178586c3d9555b8551";
    private final String AUTH_TOKEN = "5747312d1b8bdd43dddcbab098992ea6";
    private final String Twilio_PHONE = "+18108181439";
    private final int CODE_LENGTH = 6;

    private TwilioService() {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
    }

    public static TwilioService GetTwilioServiceInstance() {
        return twilioService;
    }

    private String GenerateRandomCode() {
        String randomCode = new String();
        Random random = new Random();
        int upperbound = 10;
        for (int i = 0; i < CODE_LENGTH; i++) {
            int randomInteger = random.nextInt(upperbound);
            randomCode += Integer.toString(randomInteger);
        }
        return randomCode;
    }

    public String SendConfirmationMessage(String phoneNumber) {
        String randomCode = GenerateRandomCode();
        String messageInfo = "Your Code is" + randomCode;
        Message.creator(new PhoneNumber(phoneNumber), new PhoneNumber(Twilio_PHONE), messageInfo).create();
        return randomCode;
    }

    public String SendConfirmationCall(String phoneNumber) {
        String randomCode = GenerateRandomCode();
        String callInfo = "Your Code is" + randomCode;
        Say sayInCall = new Say.Builder(callInfo).loop(3).build();
        String callInfoXML = new VoiceResponse.Builder().say(sayInCall).build().toXml();
        Call.creator( new PhoneNumber(phoneNumber), new PhoneNumber(Twilio_PHONE), callInfoXML).create();
        return randomCode;
    }

    public static void main(String[] args) {
//        String randomCode = new String();
//        Random random = new Random();
//        int upperbound = 10;
//        for (int i = 0; i < 6; i++) {
//            int randomInteger = random.nextInt(upperbound);
//            randomCode += Integer.toString(randomInteger);
//        }
//        System.out.println(randomCode);
    }

}
