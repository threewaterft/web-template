package com.threewater.webserver.webtemplate.service;

import javax.servlet.http.HttpServletRequest;

public interface LogService {
    public void insertLog(HttpServletRequest request);
    public void updateLog(HttpServletRequest request);
}
