package com.example.demo;

import com.example.demo.entities.Project;
import com.example.demo.exception.ValidationTagException;
import com.example.demo.service.ValidationService;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class TestValidationService extends TestBase {

    @Autowired
    ValidationService validationService;

    Project project = new Project();

    @Before
    public void fillMockedProject() {
        project.setId(100L);
        project.setName("testProject");
        project.resetValidationToDefault();
    }

    /**
     * Expected that any symbols can pass validation step when we use default
     * validation rules.
     * 
     * @author ake
     * 
     */
    @Test
    public void testValidationGood() throws ValidationTagException {
        validationService.validate("anySYMBOL1*-_", project);
    }

    /**
     * This test is expected to always fail because we are trying to validate a
     * string with more than 1 symbol.
     * 
     * @throws ValidationTagException
     * @author ake
     */
    @Test(expected = ValidationTagException.class)
    public void testValidationBad() throws ValidationTagException {
        project.setValidation(".{1}");
        validationService.validate("moreThanOneSymbols", project);
    }

}