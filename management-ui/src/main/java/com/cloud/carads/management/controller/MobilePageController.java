package com.cloud.carads.management.controller;

import com.cloud.carads.commons.controller.BaseController;
import com.cloud.carads.commons.entity.ErrorMsg;
import com.cloud.carads.management.entity.CAccountInfoDto;
import com.cloud.carads.management.service.IAccountService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/mobile/page")
public class MobilePageController extends BaseController {
    @Autowired
    private IAccountService accountService;

    @RequestMapping(value = "/{page}", method = RequestMethod.GET)
    public String page(Model model,
                       @PathVariable String page) {

        CAccountInfoDto accountInfoDto = new CAccountInfoDto();
        accountInfoDto.setUserName("zhangqw");
        accountInfoDto.setPassword("a123456");
        accountInfoDto.setMobileNo("15951903466");

        ErrorMsg msg = accountService.register(accountInfoDto);
        System.err.println(new Gson().toJson(msg));


        return "mobile" + "/" + page;
    }
}
