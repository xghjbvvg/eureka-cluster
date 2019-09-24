package com.example.service;

/**
 * @author 黄炽鑫
 * @date 2019-7-28
 */
public interface IMessageService {
    /**
     * 获取短信验证码
     *
     * @return
     */
    public Integer getMessageCode(String userId, String phone);

    /**
     * 判断输入验证码是否一致
     *
     * @param userId
     * @param code
     * @return
     */
    public Boolean isEquals(String userId, String code);

}
