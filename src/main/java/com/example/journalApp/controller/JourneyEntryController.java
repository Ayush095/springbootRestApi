package com.example.journalApp.controller;

import com.example.journalApp.entity.JournalEntry;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/journal")
public class JourneyEntryController {
    Map<Long, JournalEntry> journalEnteries = new HashMap<>();

    // By Param method also
    @GetMapping
    public List<JournalEntry> getAll(@RequestParam(required = false) Long journalId) {
        List<JournalEntry> response = new ArrayList<>();
        if(journalId!=null && journalEnteries.containsKey(journalId)){
            response.add(journalEnteries.get(journalId));
        } else {
            response.addAll(journalEnteries.values());
        }
        return response;
    }

    @PostMapping
    public JournalEntry createEntry(@RequestBody JournalEntry newEntry) {
        journalEnteries.put(newEntry.getId(), newEntry);
        return newEntry;
    }

    // by variable method
    @GetMapping("id/{myId}")
    public JournalEntry getJournalByIdFromPathVariable(@PathVariable Long myId) {
        return journalEnteries.get(myId);
    }

    @DeleteMapping("id/{myId}")
    public JournalEntry deleteJournalById(@PathVariable Long myId){
        return journalEnteries.remove(myId);
    }

    @PutMapping("id/{myId}")
    public JournalEntry updateJournal(@PathVariable Long myId, @RequestBody JournalEntry updatedEntry){
        return journalEnteries.put(myId, updatedEntry);
    }
}
