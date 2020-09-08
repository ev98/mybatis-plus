package com.ev.bean;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @TableId(type = IdType.AUTO)  //AUTO自增，INPUT手动输入
    private Long id;

    private String name;
    private Integer age;
    private String email;

    @Version //乐观锁
    private Integer version;

    @TableLogic //逻辑删除
    private Integer deleted;

    //字段添加填充内容
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
}
