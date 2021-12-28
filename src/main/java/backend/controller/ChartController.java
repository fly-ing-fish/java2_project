package backend.controller;


import backend.service.ChartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@CrossOrigin(allowCredentials ="true")
@RestController
@RequestMapping("/api/")
public class ChartController {
    @Autowired
    private ChartService chartService;

    @RequestMapping(value = "getLineInformation", method = RequestMethod.GET)
    @CrossOrigin(allowCredentials ="true")
    public ResponseEntity<?> getLineInformation() throws Exception {
        return chartService.getLineInformation3();

    }

    @RequestMapping(value = "updateGitHub", method = RequestMethod.POST)
    @CrossOrigin(allowCredentials ="true")
    public ResponseEntity<?> updateGiuHub() throws Exception {
        return chartService.updateGiuHub();
    }

    @RequestMapping(value = "updateWho", method = RequestMethod.POST)
    @CrossOrigin(allowCredentials ="true")
    public ResponseEntity<?> updateWho() throws Exception {
        return chartService.updateWho();
    }
    @CrossOrigin(allowCredentials ="true")
    @RequestMapping(value = "getInformation", method = RequestMethod.GET)
    public ResponseEntity<?> getInformation(int page) throws Exception {
        return chartService.getInformations(page);
    }
    @CrossOrigin(allowCredentials ="true")
    @RequestMapping(value = "sortInformation", method = RequestMethod.GET)
    public ResponseEntity<?> sortInformation(String sort) throws Exception {
        return chartService.sortInformations(sort);
    }
    @CrossOrigin(allowCredentials ="true")
    @RequestMapping(value = "searchInformation", method = RequestMethod.GET)
    public ResponseEntity<?> searchInformation(String sort,String search) throws Exception {
        return chartService.searchInformations(sort,search);
    }
    @CrossOrigin(allowCredentials ="true")
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
