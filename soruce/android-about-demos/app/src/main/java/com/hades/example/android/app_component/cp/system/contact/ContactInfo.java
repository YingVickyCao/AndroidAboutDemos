package com.hades.example.android.app_component.cp.system.contact;

import java.util.List;

public class ContactInfo {
    private String contactId;
    private String name;
    private List<String> phones;
    private List<String> emails;

    public ContactInfo() {
    }

    public ContactInfo(String contactId, String name, List<String> phones, List<String> emails) {
        this.contactId = contactId;
        this.name = name;
        this.phones = phones;
        this.emails = emails;
    }

    public String getContactId() {
        return contactId;
    }

    public void setContactId(String contactId) {
        this.contactId = contactId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getPhones() {
        return phones;
    }

    public void setPhones(List<String> phones) {
        this.phones = phones;
    }

    public List<String> getEmails() {
        return emails;
    }

    public void setEmails(List<String> emails) {
        this.emails = emails;
    }
}
