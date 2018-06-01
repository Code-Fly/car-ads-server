package com.cloud.carads.management.controller;

import com.cloud.carads.commons.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/web/page")
public class WebPageController extends BaseController {
    @RequestMapping(value = "/{directory}/{page}", method = RequestMethod.GET)
    public String page(Model model,
                        @PathVariable String directory,
                        @PathVariable String page) {
        return directory + "/" + page;
    }
}
