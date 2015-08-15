package com.dakinegroup.storesUI;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class StoreUIController {

    @RequestMapping("/view")
    public String view() {
        return "storeuimain";
    }

}
