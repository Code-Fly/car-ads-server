package com.cloud.carads.account.controller;

import com.cloud.carads.account.entity.CAccountInfo;
import com.cloud.carads.account.entity.CAccountInfoDto;
import com.cloud.carads.account.service.IAccountService;
import com.cloud.carads.account.service.ISMSService;
import com.cloud.carads.commons.controller.BaseController;
import com.cloud.carads.commons.entity.Error;
import com.cloud.carads.commons.entity.ErrorMsg;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(description = "账户注册接口", tags = "注册")
public class RegisterController extends BaseController {
    @Autowired
    private IAccountService accountService;

    @Autowired
    private ISMSService smsService;

    @PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "车主注册 返回车主的id")
    public ErrorMsg register(@ApiParam(value = "车主的基本信息")
                             @RequestBody(required = true) CAccountInfoDto accountInfo
    ) {
        // 校验验证码
        ErrorMsg smsLog = smsService.queryLastSMSByPhone(accountInfo.getMobileNo());
        //ToDO
       /* if (!accountInfo.getShortCode().equals(smsLog.getContent())){
            return new ErrorMsg(Error.SMS_SHORTCODE_ERROR, "验证码错误。");
        }*/
        // 草稿未完善信息
        accountInfo.setFlag(9);
        // 查询二级或者三级推荐人
        if (null != accountInfo.getFatherId() && accountInfo.getFatherId() > 0) {
            CAccountInfo gtemplate = new CAccountInfo();
            gtemplate.setId(accountInfo.getFatherId());

            List<CAccountInfo> infos = accountService.getList(gtemplate, 0, 0);
            if (infos.size() > 0) {
                accountInfo.setGrandId(infos.get(0).getFatherId());
                accountInfo.setGgrandId(infos.get(0).getGgrandId());
            }
        }
        accountInfo.setPassword(new StandardPasswordEncoder().encode(accountInfo.getPassword()));
//        accountInfo.setPassword(MD5Util.MD5Encode(SystemConstant.PREFIX_MD5 + accountInfo.getPassword(), "UTF-8"));
        accountService.add(accountInfo);
        return new ErrorMsg(Error.SUCCESS.getValue(), Error.SUCCESS.getReasonPhrase(), accountInfo.getId());
    }
}
