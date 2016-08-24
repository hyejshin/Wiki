package com.wiki.mail;

import javax.mail.PasswordAuthentication;
import javax.mail.Authenticator;

 

/**

 * @author viper9

 *

 */

public class SMTPAuthenticator extends Authenticator {

    public SMTPAuthenticator() {
        super();
    }

 

    public PasswordAuthentication getPasswordAuthentication() {

        String username = "shinsy11@naver.com";
        String password = "dkssudgktpdy";

        return new PasswordAuthentication(username, password);

    }

}