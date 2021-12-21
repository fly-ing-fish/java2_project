package com.ooad.oj_backend.controller;


import com.ooad.oj_backend.service.ChartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/")
public class ChartController {
    @Autowired
    private ChartService chartService;
    @RequestMapping(value = "getInformation", method = RequestMethod.GET)
    public ResponseEntity<?> getInformation() {
        return chartService.getInformation();
    }
}
