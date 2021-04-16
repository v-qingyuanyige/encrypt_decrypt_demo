package cn.vtrump.testspringboot1.pojo;

import cn.vtrump.testspringboot1.annotation.EncryptDecryptClass;
import cn.vtrump.testspringboot1.annotation.EncryptDecryptField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@EncryptDecryptClass
@ApiModel("学生实体")
public class Student {


    @ApiModelProperty("学生实体的id字段")
    private Integer id;

    @EncryptDecryptField
    @ApiModelProperty("学生实体的name字段")
    private String name;

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}