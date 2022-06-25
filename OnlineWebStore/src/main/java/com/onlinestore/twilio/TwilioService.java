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
import java.util.Random;

/**
 *
 * @author Michael Ramez
 */
public class TwilioService {

    private static final TwilioService twilioServiceInstance = new TwilioService();
    private final String ACCOUNT_SID = "ACa28f4ca4bf8073178586c3d9555b8551";
    private final String AUTH_TOKEN = "50baf2ba9bed279123c7f14e68180ddb";
    private final String TWILIO_PHONE = "+18108181439";
    private final int CODE_LENGTH = 6;

    private TwilioService() {                
    }

    public void InitTwilioService(){
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        System.out.println("com.onlinestore.twilio.TwilioService.<init>()");
    }
    
    public void DestroyTwilioService(){
        Twilio.destroy();
    }
    
    public static TwilioService GetTwilioServiceInstance() {
        return twilioServiceInstance;
    }

    public class PhoneValidationInfo {

        private boolean phoneInvalid, otherError;

        private PhoneValidationInfo() {
            this.phoneInvalid = this.otherError = false;
        }

        public boolean isPhoneInvalid() {
            return phoneInvalid;
        }

        public boolean isNoErrors() {
            return phoneInvalid == false && otherError == false;
        }

    }

    public String GenerateRandomCode() {
        String randomCode = new String();
        Random random = new Random();
        int upperbound = 10;
        for (int i = 0; i < CODE_LENGTH; i++) {
            int randomInteger = random.nextInt(upperbound);
            randomCode += Integer.toString(randomInteger);
        }
        return randomCode;
    }

    public PhoneValidationInfo ValidatePhoneNumber(String phoneNumber) {
        PhoneValidationInfo phoneValidationErrors = new PhoneValidationInfo();
        try {
            PhoneNumber.fetcher(new com.twilio.type.PhoneNumber(phoneNumber)).fetch();
        } catch (ApiException e) {
            if (e.getStatusCode() == 404) {
                phoneValidationErrors.phoneInvalid = true;
            } else {
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
    }

}
