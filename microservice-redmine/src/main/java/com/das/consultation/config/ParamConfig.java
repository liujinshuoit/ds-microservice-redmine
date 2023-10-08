package com.das.consultation.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by LJS on 2022/2/9.
 */
@Component("ParamConfig")
public class ParamConfig {

    @Value("${redmine.url}")
    private String redmineUrl;

    @Value("${redmine.apiKeyJson}")
    private String redmineApiKeyJson;

    @Value("${qywx.corpId}")
    private String qywxCorpId;

    @Value("${qywx.tokenUrl}")
    private String qywxTokenUrl;

    @Value("${qywx.userListUrl}")
    private String qywxUserListUrl;

    @Value("${qywx.userListAppSecret}")
    private String qywxUserListAppSecret;

    @Value("${qywx.redmineUserInfoUrl}")
    private String qywxRedmineUserInfoUrl;

    @Value("${qywx.redmineAppSecret}")
    private String qywxRedmineAppSecret;

    public String getRedmineUrl() {
        return redmineUrl;
    }

    public void setRedmineUrl(String redmineUrl) {
        this.redmineUrl = redmineUrl;
    }

    public String getRedmineApiKeyJson() {
        return redmineApiKeyJson;
    }

    public void setRedmineApiKeyJson(String redmineApiKeyJson) {
        this.redmineApiKeyJson = redmineApiKeyJson;
    }

    public String getQywxCorpId() {
        return qywxCorpId;
    }

    public void setQywxCorpId(String qywxCorpId) {
        this.qywxCorpId = qywxCorpId;
    }

    public String getQywxTokenUrl() {
        return qywxTokenUrl;
    }

    public void setQywxTokenUrl(String qywxTokenUrl) {
        this.qywxTokenUrl = qywxTokenUrl;
    }

    public String getQywxUserListUrl() {
        return qywxUserListUrl;
    }

    public void setQywxUserListUrl(String qywxUserListUrl) {
        this.qywxUserListUrl = qywxUserListUrl;
    }

    public String getQywxUserListAppSecret() {
        return qywxUserListAppSecret;
    }

    public void setQywxUserListAppSecret(String qywxUserListAppSecret) {
        this.qywxUserListAppSecret = qywxUserListAppSecret;
    }

    public String getQywxRedmineUserInfoUrl() {
        return qywxRedmineUserInfoUrl;
    }

    public void setQywxRedmineUserInfoUrl(String qywxRedmineUserInfoUrl) {
        this.qywxRedmineUserInfoUrl = qywxRedmineUserInfoUrl;
    }

    public String getQywxRedmineAppSecret() {
        return qywxRedmineAppSecret;
    }

    public void setQywxRedmineAppSecret(String qywxRedmineAppSecret) {
        this.qywxRedmineAppSecret = qywxRedmineAppSecret;
    }
}
