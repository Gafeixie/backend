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
public class CaseTypeId extends Model {

    private static final long serialVersionUID = 1L;

    /**
     * 主键 自增id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 案件类型id
     */
    @TableField("Case_type_id")
    private Integer caseTypeId;

    /**
     * 案件类型名称
     */
    @TableField("Case_type_name")
    private String caseTypeName;

    /**
     * 0为未删除 1为已删除 默认为0
     */
    private Boolean isDel;

    /**
     * 创建时间（默认为当前时间戳）
     */
    private LocalDateTime createDate;

    /**
     * 更新时间（随时间戳改变）
     */
    private LocalDateTime updateDate;


}
