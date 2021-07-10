package com.example.model;

import lombok.Data;

/**
 * @author: 谢佳辉
 * @date 2021/1/27 5:43 下午
 */
@Data
public class Kvtable {
    String index;
    String item_ciphertext;
    String item_hash;
    String item_sign;
}
