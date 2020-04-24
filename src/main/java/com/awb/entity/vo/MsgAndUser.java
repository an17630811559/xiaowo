package com.awb.entity.vo;

import com.awb.entity.Msg;
import com.awb.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author AAA QY102 awb
 * @description
 * @date create in 12:24 2020/4/6
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class MsgAndUser extends Msg {
    private User user;
}
