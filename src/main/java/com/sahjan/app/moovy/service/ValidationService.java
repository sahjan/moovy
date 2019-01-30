package com.sahjan.app.moovy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ValidationService {

    @Autowired
    public ValidationService() {}

    /**
     * Checks whether the MultipartFile uploaded is an XML file.
     * @param file the MultipartFile object
     * @return true if it is an XML file, false if it is not.
     */
    public boolean isXMLFile(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        String substring = fileName.substring(fileName.lastIndexOf('.'));

        if (substring.equalsIgnoreCase(".xml")) {
            return true;
        }
        return false;
    }

}
