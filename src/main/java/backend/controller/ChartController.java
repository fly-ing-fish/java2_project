package backend.controller;


import backend.service.ChartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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
        return chartService.getLineInformation1();
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
    public ResponseEntity<?> getInformation(int type,int page) throws Exception {
        return chartService.getInformations(type,page);
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
    @RequestMapping(value = "sortLatestInformation", method = RequestMethod.GET)
    public ResponseEntity<?> sortLatestInformation(String sort) throws Exception {
        return chartService.sortLatestInformations(sort);
    }
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "searchLatestInformation", method = RequestMethod.GET)
    public ResponseEntity<?> searchLatestInformation(String sort,String search) throws Exception {
        return chartService.searchLatestInformations(sort,search);
    }@CrossOrigin(origins = "*")
    @RequestMapping(value = "sortVaccinationInformation", method = RequestMethod.GET)
    public ResponseEntity<?> sortVaccinationInformation(String sort) throws Exception {
        return chartService.sortVaccinationInformation(sort);
    }
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "searchVaccinationInformation", method = RequestMethod.GET)
    public ResponseEntity<?> searchVaccinationInformation(String sort,String search) throws Exception {
        return chartService.searchVaccinationInformations(sort,search);
    }
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "sortAllInformation", method = RequestMethod.GET)
    public ResponseEntity<?> sortAllInformation(String sort) throws Exception {
        return chartService.sortAllInformation(sort);
    }
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "searchAllInformation", method = RequestMethod.GET)
    public ResponseEntity<?> searchAllInformation(String sort,String search) throws Exception {
        return chartService.searchAllInformations(sort,search);
    }
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "getAnimationInformation", method = RequestMethod.GET)
    public ResponseEntity<?> getAnimationInformation() throws Exception {
        String sort="1";
        return chartService.getAnimationInformation(sort);
    }
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "reloadInformation", method = RequestMethod.GET)
    public ResponseEntity<?> reDownloadInformation(String sort) throws Exception {
        return chartService.redownload();
    }
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "getBarInformation", method = RequestMethod.GET)
    public ResponseEntity<?> getBarInformation() throws IOException {
         return chartService.getBarInformation();
    }
    @CrossOrigin
    @RequestMapping(value = "getPieInformation", method = RequestMethod.GET)
    public ResponseEntity<?> getPieInformation() throws IOException {
        return chartService.getPieInformation();
    }
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "download1", method=RequestMethod.GET)
    public ResponseEntity<?>download(int what) throws Exception {
    return chartService.download(what);
    }
}
