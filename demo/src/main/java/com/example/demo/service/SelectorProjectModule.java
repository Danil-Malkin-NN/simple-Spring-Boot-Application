package com.example.demo.service;

import com.example.demo.exception.ModuleNotSupportedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class SelectorProjectModule {

    @Autowired
    Map<String, ProjectModule> stringProjectModuleMap = new HashMap();

    public ProjectModule getModule(String moduleName) throws ModuleNotSupportedException {
        if (stringProjectModuleMap.containsKey(moduleName))
            return stringProjectModuleMap.get(moduleName);
        throw new ModuleNotSupportedException("module not supported");
    }
}
