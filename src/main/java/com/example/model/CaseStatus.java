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
 * @since 2021-01-26
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class CaseStatus extends Model {

    private static final long serialVersionUID = 1L;

    /**
     * 主键 自增id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 案件id
     */
    @TableField("Case_id")
    private String caseId;

    /**
     * 0为未结案 1为已结案 
     */
    private Boolean status;

    /**
     * 创建时间（默认为当前时间戳）
     */
    private LocalDateTime createTime;

    /**
     * 更新时间（随时间戳改变）
     */
    private LocalDateTime updateTime;


}
