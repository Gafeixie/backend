package com.example.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.sql.Blob;
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
 * @since 2021-01-13
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Evidence extends Model {

    private static final long serialVersionUID = 1L;

    /**
     * 证据id
     */
    @TableField("Evidence_id")
    private Integer evidenceId;

    /**
     * 主键 自增id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 证据图片url
     */
    @TableField("Evidence_url")
    private String evidenceUrl;

    /**
     * 备注
     */
    private String note;

    /**
     * 可见部门ids
     */
    @TableField("Department_ids")
    private String departmentIds;

    /**
     * 案件id
     */
    @TableField("Case_id")
    private String caseId;

    /**
     * 员工id
     */
    @TableField("Staff_id")
    private String staffId;

    /**
     * 区块哈希
     */
    @TableField("Block_hash")
    private String blockHash;

    /**
     * 创建时间（默认为当前时间戳）
     */
    private LocalDateTime createDate;

    private  String examine;
    private  int isImg;
    private  int staues;

}
