package com.dakinegroup.storesApp;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StoreController {

    @RequestMapping("/create")
    public Store create(@RequestParam(value="id", defaultValue="NA") String id) {
	Store st = new Store(id);
 	st.displayInfo();
        return st;
    }
}
