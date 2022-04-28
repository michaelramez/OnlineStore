/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.onlinestore.twilio;

import com.twilio.Twilio;
import com.twilio.exception.ApiException;
import com.twilio.rest.api.v2010.account.Call;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.lookups.v1.PhoneNumber;
import com.twilio.twiml.VoiceResponse;
import com.twilio.twiml.voice.Say;
//import com.twilio.type.PhoneNumber;
import java.util.Random;

/**
 *
 * @author Michael Ramez
 */
public class TwilioService {

    private static final TwilioService twilioServiceInstance = new TwilioService();
    private final String ACCOUNT_SID = "ACa28f4ca4bf8073178586c3d9555b8551";
    private final String AUTH_TOKEN = "ffd5276a0937ef5cc16a84c9cd2f6224";
    private final String TWILIO_PHONE = "+18108181439";
    private final int CODE_LENGTH = 6;
    
    private TwilioService() {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        System.out.println("com.onlinestore.twilio.TwilioService.<init>()");
    }

    public static TwilioService GetTwilioServiceInstance() {
        return twilioServiceInstance;
    }

    public class PhoneValidationErrors{
        boolean phoneInvalid, otherError;

        private PhoneValidationErrors() {
            this.phoneInvalid = this.otherError;
        }

        public boolean isPhoneInvalid() {
            return phoneInvalid;
        }

        public boolean isOtherError() {
            return otherError;
        }

        public boolean isNoErrors() {
            return phoneInvalid == false && otherError == false;
        }
        
        
        
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

    public PhoneValidationErrors ValidatePhoneNumber(String phoneNumber) {
        PhoneValidationErrors phoneValidationErrors = new PhoneValidationErrors();
        try {
            PhoneNumber.fetcher(new com.twilio.type.PhoneNumber(phoneNumber)).fetch();
        } catch (ApiException e) {
            if (e.getStatusCode() == 404) {
                phoneValidationErrors.phoneInvalid = true;
            }
            else{
//                System.out.println("other error occured " + e.getStatusCode());
                phoneValidationErrors.otherError = true;
            }
        }
//        PhoneNumber.fetcher(new com.twilio.type.PhoneNumber(phoneNumber)).setCountryCode("EG").fetch(); 
//        return false;
        return phoneValidationErrors;
    }

    public String SendConfirmationMessage(String phoneNumber) {
        String randomCode = GenerateRandomCode();
        String messageInfo = "Your Code is" + randomCode;
        Message.creator(new com.twilio.type.PhoneNumber(phoneNumber), new com.twilio.type.PhoneNumber(TWILIO_PHONE), messageInfo).create();
        return randomCode;
    }

    public String SendConfirmationCall(String phoneNumber) {
        String randomCode = GenerateRandomCode();
        String callInfo = "Your Code is" + randomCode;
        Say sayInCall = new Say.Builder(callInfo).loop(2).build();
        String callInfoXML = new VoiceResponse.Builder().say(sayInCall).build().toXml();
        Call.creator(new com.twilio.type.PhoneNumber(phoneNumber), new com.twilio.type.PhoneNumber(TWILIO_PHONE), callInfoXML).create();
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
        TwilioService twilioService = TwilioService.GetTwilioServiceInstance();
//        int validPhone = twilioService.ValidatePhoneNumber("+203333333333");
//        System.out.println("The phone validity is : " + validPhone);
    }

}
