package com.mycompany.springbootgmail.controller;

import com.google.api.services.gmail.model.Message;
import com.mycompany.springbootgmail.dto.ModifyMessageDto;
import com.mycompany.springbootgmail.service.GmailMessageService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/messages")
public class MessageController {

    private final GmailMessageService gmailMessageService;

    public MessageController(GmailMessageService gmailMessageService) {
        this.gmailMessageService = gmailMessageService;
    }

    @GetMapping
    public List<Message> getMessages(
            @RequestParam(name = "q", required = false) String query,
            @RequestParam(name = "labelIds", required = false) List<String> labelIds) throws IOException {
        return gmailMessageService.getMessages(query, labelIds);
    }

    @GetMapping("/{messageId}")
    public Message getMessage(@PathVariable String messageId) throws IOException {
        return gmailMessageService.getMessage(messageId);
    }

    @PostMapping("/{messageId}/labels")
    public List<String> modifyMessageLabels(@PathVariable String messageId,
                                            @RequestBody ModifyMessageDto modifyMessageDto) throws IOException {
        return gmailMessageService.modifyMessageLabels(messageId, modifyMessageDto.getLabelsToAdd(), modifyMessageDto.getLabelsToRemove());
    }

}
