package com.sahjan.app.moovy.controller;
import com.sahjan.app.moovy.payload.Movie;
import com.sahjan.app.moovy.exception.UploadException;
import com.sahjan.app.moovy.service.MovieService;
import com.sahjan.app.moovy.service.ValidationService;
import com.sahjan.app.moovy.service.XMLParserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.NoSuchFileException;
import java.util.List;

@RestController
public class MoovyController {

    @Autowired
    private ValidationService validationService;
    @Autowired
    private XMLParserService xmlParserService;
    @Autowired
    private MovieService movieService;

    @PostMapping("/uploadMovies" )
    public String uploadMovies(@RequestParam("file") MultipartFile file) {
        try {
            //check if there is a file and if it is the right type
            if (!file.isEmpty() && validationService.isXMLFile(file)) {
                InputStream xmlData = file.getInputStream();
                movieService.setMovieList(xmlParserService.parse(xmlData));
            }
            else {
                throw new UploadException("UploadException: Unable to upload file.");
            }
        } catch(UploadException | IOException e) {
            System.out.println(e.getMessage());
            return e.getMessage();
        }
        return "Movies successfully uploaded! Start using Moodslider.";
    }

    @GetMapping("/getMovies")
    public ResponseEntity getMovies(@RequestParam("acValue") int acValue,
                                    @RequestParam("hsValue") int hsValue,
                                    @RequestParam("twaValue") int twaValue,
                                    @RequestParam("sfValue") int sfValue) {
        try {
            List<Movie> movies = movieService.selectMovies(acValue, hsValue, twaValue, sfValue);
            return new ResponseEntity<>(movies, HttpStatus.OK);
        }
        catch(NoSuchFileException e) {
            e.printStackTrace();
        }
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
