package com.dakinegroup.storesUI;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class StoreUIController {

    @RequestMapping("/view2")
    public String view2() {
        return "storeuimain";
    }

}
