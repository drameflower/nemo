package com.netease.nemo.controller.socialChat;

import com.netease.nemo.annotation.RestResponseBody;
import com.netease.nemo.annotation.TokenAuth;
import com.netease.nemo.code.ErrorCode;
import com.netease.nemo.context.Context;
import com.netease.nemo.dto.UserDto;
import com.netease.nemo.exception.BsException;
import com.netease.nemo.socialchat.dto.OnLineUserDto;
import com.netease.nemo.socialchat.dto.UserRewardDto;
import com.netease.nemo.socialchat.parameter.GetOnLineUserListParam;
import com.netease.nemo.socialchat.parameter.GetUserInfoParam;
import com.netease.nemo.socialchat.parameter.GetUserStateParam;
import com.netease.nemo.socialchat.parameter.UserRewardParam;
import com.netease.nemo.socialchat.service.OneToOneChatService;
import com.netease.nemo.util.AssertUtil;
import com.netease.nemo.util.ObjectMapperUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * 云信派对1v1娱乐社交APP演示API
 */
@RestController
@RequestMapping("/nemo/socialChat/user")
@Slf4j
@RestResponseBody
@TokenAuth
public class OneToOneChatController {

    @Resource
    private OneToOneChatService oneToOneChatService;

    @RequestMapping("/reporter")
    public void reporter() {
        oneToOneChatService.reporter(Context.get().getUserUuid(), Context.get().getDeviceId());
    }

    @RequestMapping("/getOnlineUser")
    public List<OnLineUserDto> getUserList(@RequestBody GetOnLineUserListParam param) {
        AssertUtil.notNull(param.getPageNum(), ErrorCode.BAD_REQUEST, "PageNumber Can Not Null");
        AssertUtil.notNull(param.getPageSize(), ErrorCode.BAD_REQUEST, "PageSize Can Not Null");
        AssertUtil.isTrue(param.getPageNum() >= 0, ErrorCode.BAD_REQUEST, "PageNumber is invalid.");
        AssertUtil.isTrue(param.getPageSize() >= 1 && param.getPageSize() <= 50, ErrorCode.BAD_REQUEST, "PageSize is invalid or exceeds the maximum");
        return oneToOneChatService.getOnLineUser(param.getPageNum(), param.getPageSize());
    }

    @RequestMapping("/getUserState")
    public String getUserState(@Valid @RequestBody GetUserStateParam param) {
        return oneToOneChatService.getUserState(param.getMobile());
    }

    @RequestMapping("/reward")
    public void userReward(@Valid @RequestBody UserRewardParam param) {
        UserRewardDto userRewardDto = ObjectMapperUtil.map(param, UserRewardDto.class);
        userRewardDto.setUserUuid(Context.get().getUserUuid());

        if(Context.get().getUserUuid().equals(param.getTarget())) {
            throw new BsException(ErrorCode.BAD_REQUEST, "用户不能给自己打赏");
        }

        oneToOneChatService.userReward(userRewardDto);
    }

    @RequestMapping("/getUserInfo")
    public UserDto getUserInfo(@Valid @RequestBody GetUserInfoParam param) {
        return oneToOneChatService.getUserInfo(param.getUserUuid(), param.getDeviceId());
    }
}
