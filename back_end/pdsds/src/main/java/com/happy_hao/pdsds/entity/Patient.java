package com.happy_hao.pdsds.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
public class Patient extends User {

    protected int doctor_id;
    protected String doctor_nickname;
}
