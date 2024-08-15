package com.example.demospring.demomanyto_many.controller;

import com.example.demospring.demomanyto_many.model.APIResponse;
import com.example.demospring.demomanyto_many.model.Card;
import com.example.demospring.demomanyto_many.model.request.CardRequest;
import com.example.demospring.demomanyto_many.service.CardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/cards")
public class CardController {
    private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping
    public ResponseEntity<?> getAllCard (){
        List<Card> cards = cardService.getAllCard();
        APIResponse<?> apiResponse = new APIResponse<>(
                "Successfully",
                cards,
                HttpStatus.OK,
                LocalDateTime.now()
        );
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getByIdCard(@PathVariable Integer id){
        Optional<Card> card = cardService.getByIdCard(id);
        APIResponse<?> apiResponse = new APIResponse<>(
                "Successfully",
                card,
                HttpStatus.OK,
                LocalDateTime.now()
        );
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping
    public ResponseEntity<?> createCard (@RequestBody CardRequest cardRequest){
        Card card1 = cardService.createCard(cardRequest);
        APIResponse<?> apiResponse = new APIResponse<>(
                "Successfully",
                card1,
                HttpStatus.CREATED,
                LocalDateTime.now()
        );
        return ResponseEntity.ok(apiResponse);
    }

    @PutMapping("/{id}")
    private ResponseEntity<?> updateCard(@RequestBody CardRequest cardRequest ,@PathVariable Integer id){
        Card card = cardService.updateCard(cardRequest,id);
        APIResponse<?> apiResponse = new APIResponse<>(
                "Successfully",
                card,
                HttpStatus.OK,
                LocalDateTime.now()
        );
        return ResponseEntity.ok(apiResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCard(@PathVariable Integer id){
        Optional<Card> card = cardService.deleteCard(id);
        APIResponse<?> apiResponse = new APIResponse<>(
                "Successfully",
                card,
                HttpStatus.OK,
                LocalDateTime.now()
        );
        return ResponseEntity.ok(apiResponse);
    }

}
