package com.example.demospring.demomanyto_many.service;

import com.example.demospring.demomanyto_many.model.Card;
import com.example.demospring.demomanyto_many.model.request.CardRequest;

import java.util.List;
import java.util.Optional;

public interface CardService {
    List<Card> getAllCard();

    Optional<Card> getByIdCard(Integer id);

    Card createCard(CardRequest cardRequest);

    Card updateCard(CardRequest cardRequest, Integer id);

    Optional<Card> deleteCard(Integer id);
}
