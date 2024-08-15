package com.example.demospring.demomanyto_many.service.serviceImp;

import com.example.demospring.demomanyto_many.exception.CustomNotfoundException;
import com.example.demospring.demomanyto_many.model.Card;
import com.example.demospring.demomanyto_many.model.request.CardRequest;
import com.example.demospring.demomanyto_many.repository.CardRepository;
import com.example.demospring.demomanyto_many.service.CardService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CardServiceImp implements CardService {
    private final CardRepository cardRepository;

    public CardServiceImp(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }


    @Override
    public List<Card> getAllCard() {
        return cardRepository.findAll();
    }

    @Override
    public Optional<Card> getByIdCard(Integer id) {
        Optional<Card> card = cardRepository.findById(id);
        if(!card.isPresent()){
            throw new CustomNotfoundException("Not Found");
        }
        return card;
    }

    @Override
    public Card createCard(CardRequest cardRequest) {
        Card card1 = new Card();
        card1.setNumber(cardRequest.getNumber());
        cardRepository.save(card1);
        return card1;
    }

    @Override
    public Card updateCard(CardRequest cardRequest, Integer id) {
        Optional<Card> card = cardRepository.findById(id);
        if(!card.isPresent()){
            throw new CustomNotfoundException("Not Found");
        }
        Card card1 = new Card();
        card1.setNumber(cardRequest.getNumber());
        cardRepository.save(card1);
        return card1;
    }

    @Override
    public Optional<Card> deleteCard(Integer id) {
        Optional<Card> card = cardRepository.findById(id);
        if(!card.isPresent()){
            throw new CustomNotfoundException("Not Found");
        }
        cardRepository.deleteById(id);
        return card;
    }
}
