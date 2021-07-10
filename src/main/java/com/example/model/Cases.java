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
public class Cases extends Model {

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
     * 案件名
     */
    @TableField("Case_name")
    private String caseName;

    /**
     * 案件类型id
     */
    @TableField("Case_type_id")
    private String caseTypeId;

    /**
     * 案情描述
     */
    @TableField("Case_description")
    private String caseDescription;

    /**
     * 重要程度 0为一般 1为较大 2为重大 3为特别重大
     */
    private int importace;

    /**
     * 员工id
     */
    @TableField("Staff_id")
    private String staffId;

    /**
     * 部门可见ids
     */
    @TableField("Department_ids")
    private String departmentIds;

    /**
     * 区块哈希
     */
    @TableField("Block_Hash")
    private String blockHash;

    /**
     * 创建时间（默认为当前时间戳）
     */
    private LocalDateTime createDate;

    /**
     * 更新时间（随时间戳改变）
     */
    private LocalDateTime updateDate;


}
