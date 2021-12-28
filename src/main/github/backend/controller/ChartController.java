package backend.controller;


import backend.service.ChartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@CrossOrigin(origins = "*")
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
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "getInformation", method = RequestMethod.GET)
    public ResponseEntity<?> getInformation(int page) throws Exception {
        return chartService.getInformations(page);
    }
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "sortInformation", method = RequestMethod.GET)
    public ResponseEntity<?> sortInformation(String sort) throws Exception {
        return chartService.sortInformations(sort);
    }
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "searchInformation", method = RequestMethod.GET)
    public ResponseEntity<?> searchInformation(String sort,String search) throws Exception {
        return chartService.searchInformations(sort,search);
    }
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "reloadInformation", method = RequestMethod.GET)
    public ResponseEntity<?> reDownloadInformation(String sort) throws Exception {
        return chartService.redownload();
    }
//    @CrossOrigin
//    @RequestMapping(value = "getBarInformation", method = RequestMethod.GET)
//    public ResponseEntity<?> getBarInformation() {
//        return chartService.getBarInformation();
//    }
}
