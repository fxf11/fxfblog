package com.fxf.blogbs.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.sun.istack.internal.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sort implements Serializable {


    @TableId
    private int sortId;
    @NotNull
    private String sortName;
}
