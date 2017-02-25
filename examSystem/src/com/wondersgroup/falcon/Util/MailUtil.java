/**
 * <p>Title: ������̳</p>
 * <p>Description: ������̳���������ʼ�</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: ���㹤����</p>
 * @author ����
 * @version 2.0
 */

package com.wondersgroup.falcon.Util;

import java.util.*;
import org.apache.log4j.Logger;

public final class MailUtil {

    private static Logger log = Logger.getLogger(MailUtil.class);

    private MailUtil() {// prevent instantiation
    }

    private static MailOptions mailOption = new MailOptions();

    public static void checkGoodEmail(String input) throws Exception {
        if (input == null) throw new Exception("Sorry, null string is not a good email.");
        int atIndex = input.indexOf('@');
        int dotIndex = input.lastIndexOf('.');
        if ((atIndex == -1) || (dotIndex == -1) || (atIndex >= dotIndex))
            //throw new Exception("Error: '" + DisableHtmlTagFilter.filter(input) + "' is not a valid email value. Please try again.");
			throw new Exception("Error: '" + input + "' is not a valid email value. Please try again.");
        // now check for content of the string
        byte[] s = input.getBytes();
        int length = s.length;
        byte b = 0;

        for (int i = 0; i < length; i++) {
            b = s[i];
            if ((b >= 'a') && (b <= 'z')) {
                // lower char
            } else if ((b >= 'A') && (b <= 'Z')) {
                // upper char
            } else if ((b >= '0') && (b <= '9') && (i != 0)) {
                // numeric char
            } else if ( ( (b=='_') || (b=='-') || (b=='.') || (b=='@') ) && (i != 0) ) {
                // _ char
            } else {
                // not good char, throw an Exception
                throw new Exception(input + " is not a valid email. Reason: character '" + (char)(b) + "' is not accepted in an email.");
            }
        }// for

        // last check
        try {
            new javax.mail.internet.InternetAddress(input);
        } catch (Exception ex) {
            log.error("Error when running checkGoodEmail", ex);
            throw new Exception("Assertion: dont want to occur in Util.checkGoodEmail");
        }
    }


    public static void sendMail(String from, String to, String cc, String bcc, String subject, String message)
        throws MessagingException, Exception {

        if (from == null) from = mailOption.defaultMailFrom;

        // this will also check for email error
        checkGoodEmail(from);
        InternetAddress fromAddress = new InternetAddress(from);
        InternetAddress[] toAddress = getInternetAddressEmails(to);
        InternetAddress[] ccAddress = getInternetAddressEmails(cc);
        InternetAddress[] bccAddress= getInternetAddressEmails(bcc);
        if ((toAddress == null) && (ccAddress == null) && (bccAddress == null)) {
            throw new Exception("Cannot send mail since all To, Cc, Bcc addresses are empty.");
        }

        Properties props = new Properties();
        props.put("mail.smtp.host", mailOption.mailServer);
        props.put("mail.smtp.port", String.valueOf(mailOption.port));
        if ( (mailOption.username != null) && (mailOption.username.length() > 0) ) {
            props.put("mail.smtp.auth", "true");
            //props.put("mail.user", mailOption.mailServer);
            //props.put("mail.password", mailOption.mailServer);
        }
        //props.put("mail.debug", "true");

        Transport transport = null;
        try {
            Session session = Session.getDefaultInstance(props, null);
            transport = session.getTransport("smtp");
            if ( (mailOption.username != null) && (mailOption.username.length() > 0) ) {
                transport.connect(mailOption.mailServer, mailOption.username, mailOption.password);
            } else {
                transport.connect();
            }
            // create a message
            Message msg = new MimeMessage(session);
            msg.setSentDate(new Date());
            msg.setFrom(fromAddress);
            if (toAddress != null) msg.setRecipients(Message.RecipientType.TO, toAddress);
            if (ccAddress != null) msg.setRecipients(Message.RecipientType.CC, ccAddress);
            if (bccAddress!= null) msg.setRecipients(Message.RecipientType.BCC, bccAddress);
            msg.setSubject(subject);
            msg.setText(message);
            msg.saveChanges();

            transport.sendMessage(msg, msg.getAllRecipients());
        } catch (MessagingException mex) {
            log.error("MessagingException has occured.", mex);
            log.debug("MessagingException has occured. Detail info:");
            log.debug("from = " + from);
            log.debug("to = " + to);
            log.debug("cc = " + cc);
            log.debug("bcc = " + bcc);
            log.debug("subject = " + subject);
            log.debug("message = " + message);
            throw mex;// this may look redundant
        } finally {
            try {
                if (transport != null) transport.close();
            } catch (MessagingException ex) { }
        }
    }

    /**
     * This method trim the email variable, so if it contains only spaces,
     * then it will be empty string, then we have 0 token :-)
     * The returned value is never null
     */
    private static String[] getEmails(String email) throws Exception {
        if (email == null) email = "";
        email = email.trim();// very important
        StringTokenizer t = new StringTokenizer(email, ";");
        String[] ret = new String[t.countTokens()];
        int index = 0;
        while(t.hasMoreTokens()) {
            String mail = t.nextToken().trim();
            checkGoodEmail(mail);
            ret[index] = mail;
            //log.debug(ret[index]);
            index++;
        }
        return ret;
    }

    /*
    private static String[] getEmails(String to, String cc, String bcc) throws Exception {
        String[] toMail = getEmails(to);
        String[] ccMail = getEmails(cc);
        String[] bccMail= getEmails(bcc);
        String[] ret = new String[toMail.length + ccMail.length + bccMail.length];
        int index = 0;
        for (int i = 0; i < toMail.length; i++) {
            ret[index] = toMail[i];
            index++;
        }
        for (int i = 0; i < ccMail.length; i++) {
            ret[index] = ccMail[i];
            index++;
        }
        for (int i = 0; i < bccMail.length; i++) {
            ret[index] = bccMail[i];
            index++;
        }
        return ret;
    }
    */

    /**
     * This method will return null if there is not any email
     *
     * @param email
     * @return
     * @throws Exception
     * @throws AddressException
     */
    private static InternetAddress[] getInternetAddressEmails(String email)
        throws Exception, AddressException {
        String[] mails = getEmails(email);
        if (mails.length == 0) return null;// must return null, not empty array

        //log.debug("to = " + mails);
        InternetAddress[] address = new InternetAddress[mails.length];
        for (int i = 0; i < mails.length; i++) {
            address[i] = new InternetAddress(mails[i]);
            //log.debug("to each element = " + mails[i]);
        }
        return address;
    }
}
