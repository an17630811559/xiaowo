package com.awb.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * (Msg)实体类
 *
 * @author awb
 * @since 2020-04-04 14:58:31
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class Msg implements Serializable {
    private static final long serialVersionUID = -72405320805468930L;
        private Integer id;
        private String time;
        private String msg;
        private Integer uid;
        private Integer status;
        private String delDate;
}