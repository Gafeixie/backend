package com.example.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author astupidcoder
 * @since 2021-01-12
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Staff extends Model {

    private static final long serialVersionUID = 1L;

    /**
     * 主键，自增id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 员工id
     */
    @TableField("Staff_id")
    private String staffId;

    /**
     * 部门id 101公安 102检察院 103法院 104司法
     */
    @TableField("Department_id")
    private Integer departmentId;

    /**
     * 员工姓名
     */
    @TableField("Staff_name")
    private String staffName;

    /**
     * 角色id
     */
    @TableField("Role_id")
    private Integer roleId;

    /**
     * 性别 1男 0女 默认为0
     */
    private Boolean gender;

    /**
     * 手机号
     */
    @TableField("Phone")
    private String Phone;

    /**
     * 0为未删除 1为已删除 默认为0
     */
    private Boolean isDel;

    /**
     * 创建时间（默认为当前时间戳）
     */
    private LocalDateTime createDate;

    /**
     * 更新时间（随当前时间戳改变）
     */
    private LocalDateTime updateDate;


}
