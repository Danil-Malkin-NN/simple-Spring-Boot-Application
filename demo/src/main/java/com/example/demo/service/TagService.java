package com.example.demo.service;

import com.example.demo.Mapper.Mapper;
import com.example.demo.dto.PriceTagDto;
import com.example.demo.dto.TagDto;
import com.example.demo.entities.Currency;
import com.example.demo.entities.Tag;
import com.example.demo.exception.NoEntitiesException;
import com.example.demo.repositories.CurrencyRepository;
import com.example.demo.repositories.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService {

    @Autowired
    TagRepository tagRepository;

    @Autowired
    CurrencyRepository currencyRepository;

    public Tag getTag(Long id) throws NoEntitiesException {
        return tagRepository.findById(id).orElseThrow(() -> new NoEntitiesException("Tag not found"));
    }

    public TagDto getTagDto(Long id) throws NoEntitiesException {
        return Mapper.modelMapper.map(getTag(id), TagDto.class);
    }

    public void deleteTag(Long id) throws NoEntitiesException {
        tagRepository.delete(getTag(id));
    }

    public void setCountForTag(Long id, Integer count) throws NoEntitiesException {
        Tag tag = getTag(id);
        tag.setCount(count);
        tagRepository.save(tag);
    }

    public void setPriceForTag(Long id, Integer price) throws NoEntitiesException {
        Tag tag = getTag(id);
        tag.setPrice(price);
        tagRepository.save(tag);
    }

    public Integer getCount(Long id) throws NoEntitiesException {
        return getTag(id).getCount();
    }

    public PriceTagDto getPrice(Long id) throws NoEntitiesException {
        Mapper mapper = new Mapper();
        PriceTagDto priceTagDto = Mapper.modelMapper.map(getTag(id), PriceTagDto.class);
        List<Currency> currencyList = currencyRepository.findAll();
        Double rub = priceTagDto.getPrices().get("RUB");
        for (Currency c : currencyList) {
            priceTagDto.getPrices().put(c.getName(), rub / c.getValue());
            System.out.println(priceTagDto.getPrices().get("RUB") / c.getValue());
        }

        return priceTagDto;
    }
}
