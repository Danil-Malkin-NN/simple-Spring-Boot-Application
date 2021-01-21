package com.example.demo.controller;

import com.example.demo.dto.TagNameDto;
import com.example.demo.exception.NoEntitiesException;
import com.example.demo.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/Tag")
public class TagController {

    @Autowired
    TagService tagService;

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void deleteTagInProject(@PathVariable(value = "id") Long id) throws NoEntitiesException {
        tagService.deleteTag(id);
    }

    @RequestMapping(value = "{id}/count", method = RequestMethod.POST)
    public void setCountForTag(@PathVariable(value = "id") Long id, @RequestParam(value = "count") Integer count) throws
                                                                                                                  NoEntitiesException {
        tagService.setCountForTag(id, count);
    }

    @RequestMapping(value = "{id}/count", method = RequestMethod.GET)
    public Integer getCountForTag(@PathVariable(value = "id") Long id) throws NoEntitiesException {
        return tagService.getCount(id);
    }

    @RequestMapping(value = "{id}/price", method = RequestMethod.POST)
    public void setPriceForTag(@PathVariable(value = "id") Long id, @RequestParam(value = "price") Integer price) throws
                                                                                                                  NoEntitiesException {
        tagService.setPriceForTag(id, price);
    }

    @RequestMapping(value = "{id}/price", method = RequestMethod.GET)
    public TagNameDto getPriceForTag(@PathVariable(value = "id") Long id) throws NoEntitiesException {
        return tagService.getPrice(id);
    }

}
