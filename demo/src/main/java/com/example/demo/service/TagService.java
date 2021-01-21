package com.example.demo.service;

import com.example.demo.Mapper.Mapper;
import com.example.demo.dto.TagNameDto;
import com.example.demo.entities.Kurs;
import com.example.demo.entities.Tag;
import com.example.demo.exception.NoEntitiesException;
import com.example.demo.repositories.KursRepository;
import com.example.demo.repositories.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TagService {

    @Autowired
    TagRepository tagRepository;

    @Autowired
    KursRepository kursRepository;

    public Tag getTag(Long id) throws NoEntitiesException {
        return tagRepository.findById(id).orElseThrow(() -> new NoEntitiesException("Tag not found"));
    }

    public TagNameDto getTagDto(Long id) throws NoEntitiesException {
        return Mapper.getTagDto(getTag(id));
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

    public TagNameDto getPrice(Long id) throws NoEntitiesException {
        TagNameDto tagNameDto = getTagDto(id);
        Kurs kurs = kursRepository.findById(1L).get();
        Double USD = kurs.getStringCurrencyMap().get("USD").getValue();
        Double EUR = kurs.getStringCurrencyMap().get("EUR").getValue();
        tagNameDto.setUSD((tagNameDto.getPrice() / USD));
        tagNameDto.setEUR(tagNameDto.getPrice() / EUR);

        return tagNameDto;
    }
}
