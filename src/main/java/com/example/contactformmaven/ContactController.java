
package com.example.contactformmaven;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/contacts")
@CrossOrigin
public class ContactController {

    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping
    public List<Contact> getContacts() {
        return contactService.getAllContacts();
    }

    @PostMapping
    public Contact addContact(@RequestBody Contact contact) {
        return contactService.saveContact(contact);
    }
}
