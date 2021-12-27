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
    public ResponseEntity<?> getLineInformation() throws Exception {
//        return chartService.getLineInformation3();
        return  null;
    }
    @RequestMapping(value = "updateGiuHub", method = RequestMethod.PUT)
    public ResponseEntity<?> updateGiuHub() throws Exception {
        return chartService.updateGiuHub();
    }

    @RequestMapping(value = "updateWho", method = RequestMethod.PUT)
    public ResponseEntity<?> updateWho() throws Exception {
        return chartService.updateWho();
    }
//    @CrossOrigin
//    @RequestMapping(value = "getBarInformation", method = RequestMethod.GET)
//    public ResponseEntity<?> getBarInformation() {
//        return chartService.getBarInformation();
//    }
}
