package com.example.model.form;

import lombok.Data;

import java.util.Date;

/**
 * @author: 谢佳辉
 * @date 2021/4/29 5:58 下午
 */
@Data
public class Messages {
    String key;
    String evidencId;
    String name;
    String value;
    Date date;
}
