/*
 * *
 *  * Copyright (c) 2016, China Construction Bank Co., Ltd. All rights reserved.
 *  * 中国建设银行版权所有.
 *  * <p/>
 *  * 审核人：
 *
 */

package com.threewater.webserver.webtemplate.util.sequence;

public interface Sequence {

    String id();

    long nextValue();

}
