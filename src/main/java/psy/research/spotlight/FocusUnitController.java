package psy.research.spotlight;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FocusUnitController {

    @GetMapping("/units")
    ResponseEntity<String> getFocusUnits() {
        return new ResponseEntity<>("hello", HttpStatus.OK);
    }
}
