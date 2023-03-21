package com.epam.rd.contactbook;

import java.util.Objects;

public class Contact {
    private NameContactInfo contactName;
    private String name;
    private Email[] emails;
    private int emailCapacity = 3;
    private int socialCapacity = 5;

    private ContactInfo telephone;
    private Social[] social;

    public Contact(String contactName) {
        this.contactName = new NameContactInfo(contactName);
        this.emails = new Email[emailCapacity];
        this.social = new Social[socialCapacity];
    }

    public void rename(String newName) {
        if (Objects.equals(newName, "") || newName == null)
            return;

        contactName.setContactName(newName);
    }

    public Email addEmail(String localPart, String domain) {
        if (emailCapacity > 0) {
            Email email = new Email(localPart, domain);
            emails[emails.length - emailCapacity] = email;
            emailCapacity--;
            return email;
        }
        return null;
    }


    public Email addEpamEmail(String firstname, String lastname) {
        String name = firstname + "_" + lastname;

        if (emailCapacity == 0) {
            return null;
        }
        Email epamEmail = new Email(name, "epam.com") {
            @Override
            public String getTitle() {
                return "Epam Email";
            }
        };

        emails[emails.length - emailCapacity] = epamEmail;
        emailCapacity--;

        return epamEmail;
    }

    public ContactInfo addPhoneNumber(int code, String number) {
        if (telephone != null) {
            return null;
        }

        ContactInfo telephone = new ContactInfo() {
            @Override
            public String getTitle() {
                return "Tel";
            }

            @Override
            public String getValue() {
                return "+" + code + " " + number;
            }
        };

        this.telephone = telephone;
        return telephone;
    }

    public Social addTwitter(String twitterId) {
        if (socialCapacity == 0) {
            return null;
        }

        Social twitter = new Social("Twitter", twitterId);
        social[social.length - socialCapacity] = twitter;
        socialCapacity--;
        return twitter;
    }

    public Social addInstagram(String instagramId) {
        if (socialCapacity == 0) {
            return null;
        }

        Social instagram = new Social("Instagram", instagramId);
        social[social.length - socialCapacity] = instagram;
        socialCapacity--;
        return instagram;
    }

    public Social addSocialMedia(String title, String id) {
        if (socialCapacity == 0) {
            return null;
        }

        Social social1 = new Social(title, id);
        social[social.length - socialCapacity] = social1;
        socialCapacity--;
        return social1;
    }

    public ContactInfo[] getInfo() {
        int capacity = 1 + social.length - socialCapacity + emails.length - emailCapacity;
        if (telephone != null)
            capacity++;

        int index = 1;

        ContactInfo[] contacts = new ContactInfo[capacity];
        contacts[0] = contactName;

        if (telephone != null) {
            contacts[index] = telephone;
            index++;
        }

        for (Email email : emails) {
            if (email != null) {
                contacts[index] = email;
                index++;
            }
        }

        for (Social social1 : social) {
            if (social1 != null) {
                contacts[index] = social1;
                index++;
            }
        }

        return contacts;
    }

    private class NameContactInfo implements ContactInfo {
        public NameContactInfo(String contactName) {
            name = contactName;
        }

        @Override
        public String getTitle() {
            return "Name";
        }

        @Override
        public String getValue() {
            return name;
        }

        public void setContactName(String contactName) {
            name = contactName;
        }
    }

    public static class Email implements ContactInfo {
        private String localPart;
        private String domain;

        public Email(String localPart, String domain) {
            this.localPart = localPart;
            this.domain = domain;
        }

        @Override
        public String getTitle() {
            return "Email";
        }

        @Override
        public String getValue() {
            return localPart + "@" + domain;
        }
    }

    public static class Social implements ContactInfo {

        private String title;
        private String id;

        public Social(String title, String id) {
            this.title = title;
            this.id = id;
        }

        @Override
        public String getTitle() {
            return title;
        }

        @Override
        public String getValue() {
            return id;
        }
    }
}