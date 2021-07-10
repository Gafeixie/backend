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
public class Login extends Model {

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
     * 登陆密码
     */
    @TableField("Password")
    private String Password;

    /**
     * 登录Token
     */
    @TableField("Token")
    private String Token;

    /**
     * 0为未删除，1为已删除，默认为0
     */
    private Boolean isDel;

    /**
     * 创建时间（默认为当前时间戳）
     */
    private LocalDateTime createDate;

    /**
     * 更新时间（随时间戳改变）
     */
    private LocalDateTime upDate;


}
