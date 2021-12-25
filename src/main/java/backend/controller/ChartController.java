package backend.controller;


import backend.service.ChartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@CrossOrigin
@RequestMapping("/api/")
public class ChartController {
    @Autowired
    private ChartService chartService;
    @CrossOrigin
    @RequestMapping(value = "getLineInformation", method = RequestMethod.GET)
    public ResponseEntity<?> getLineInformation() throws IOException {
        return chartService.getLineInformation4();
    }
//    @CrossOrigin
//    @RequestMapping(value = "getBarInformation", method = RequestMethod.GET)
//    public ResponseEntity<?> getBarInformation() {
//        return chartService.getBarInformation();
//    }
}
