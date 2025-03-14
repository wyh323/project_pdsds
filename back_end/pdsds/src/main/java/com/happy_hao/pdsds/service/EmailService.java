package com.happy_hao.pdsds.service;

import com.happy_hao.pdsds.common.Result;

public interface EmailService {

    Result getCode(String username, String email);

}
