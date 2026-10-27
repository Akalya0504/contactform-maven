
package com.example.contactformmaven;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ContactService {

    private final ObjectMapper mapper = new ObjectMapper();
    private final File file = new File("contacts.json");

    public List<Contact> getAllContacts() {
        try {
            if (!file.exists()) {
                return new ArrayList<>();
            }
            return mapper.readValue(
                    file, new TypeReference<List<Contact>>() {}
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Contact saveContact(Contact contact) {
        List<Contact> contacts = getAllContacts();
        contacts.add(contact);

        try {
            mapper.writerWithDefaultPrettyPrinter()
                    .writeValue(file, contacts);
            return contact;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
