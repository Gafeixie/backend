package com.example.util;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: 谢佳辉
 * @date 2021/5/3 10:03 下午
 */

public  class DepartmentId {
    public static Map<String,String> map;
   static{
        map =new HashMap<>();
        map.put("101","公安部门");
        map.put("102","检察院部门");
        map.put("103","法院部门");
        map.put("104","司法局部门");
    }

        public  static  String getById(String id){
            return map.get(id);
        }


}
