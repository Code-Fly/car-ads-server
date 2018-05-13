package com.cloud.carads.capp.entity;

public class CAppVersion {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column c_app_version.id
     *
     * @mbg.generated Sun May 13 23:41:21 CST 2018
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column c_app_version.version_code
     *
     * @mbg.generated Sun May 13 23:41:21 CST 2018
     */
    private String versionCode;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column c_app_version.version_name
     *
     * @mbg.generated Sun May 13 23:41:21 CST 2018
     */
    private String versionName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column c_app_version.update_time
     *
     * @mbg.generated Sun May 13 23:41:21 CST 2018
     */
    private Integer updateTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column c_app_version.app_size
     *
     * @mbg.generated Sun May 13 23:41:21 CST 2018
     */
    private String appSize;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column c_app_version.strong_upt
     *
     * @mbg.generated Sun May 13 23:41:21 CST 2018
     */
    private Integer strongUpt;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column c_app_version.download_url
     *
     * @mbg.generated Sun May 13 23:41:21 CST 2018
     */
    private String downloadUrl;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column c_app_version.id
     *
     * @return the value of c_app_version.id
     *
     * @mbg.generated Sun May 13 23:41:21 CST 2018
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column c_app_version.id
     *
     * @param id the value for c_app_version.id
     *
     * @mbg.generated Sun May 13 23:41:21 CST 2018
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column c_app_version.version_code
     *
     * @return the value of c_app_version.version_code
     *
     * @mbg.generated Sun May 13 23:41:21 CST 2018
     */
    public String getVersionCode() {
        return versionCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column c_app_version.version_code
     *
     * @param versionCode the value for c_app_version.version_code
     *
     * @mbg.generated Sun May 13 23:41:21 CST 2018
     */
    public void setVersionCode(String versionCode) {
        this.versionCode = versionCode == null ? null : versionCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column c_app_version.version_name
     *
     * @return the value of c_app_version.version_name
     *
     * @mbg.generated Sun May 13 23:41:21 CST 2018
     */
    public String getVersionName() {
        return versionName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column c_app_version.version_name
     *
     * @param versionName the value for c_app_version.version_name
     *
     * @mbg.generated Sun May 13 23:41:21 CST 2018
     */
    public void setVersionName(String versionName) {
        this.versionName = versionName == null ? null : versionName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column c_app_version.update_time
     *
     * @return the value of c_app_version.update_time
     *
     * @mbg.generated Sun May 13 23:41:21 CST 2018
     */
    public Integer getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column c_app_version.update_time
     *
     * @param updateTime the value for c_app_version.update_time
     *
     * @mbg.generated Sun May 13 23:41:21 CST 2018
     */
    public void setUpdateTime(Integer updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column c_app_version.app_size
     *
     * @return the value of c_app_version.app_size
     *
     * @mbg.generated Sun May 13 23:41:21 CST 2018
     */
    public String getAppSize() {
        return appSize;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column c_app_version.app_size
     *
     * @param appSize the value for c_app_version.app_size
     *
     * @mbg.generated Sun May 13 23:41:21 CST 2018
     */
    public void setAppSize(String appSize) {
        this.appSize = appSize == null ? null : appSize.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column c_app_version.strong_upt
     *
     * @return the value of c_app_version.strong_upt
     *
     * @mbg.generated Sun May 13 23:41:21 CST 2018
     */
    public Integer getStrongUpt() {
        return strongUpt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column c_app_version.strong_upt
     *
     * @param strongUpt the value for c_app_version.strong_upt
     *
     * @mbg.generated Sun May 13 23:41:21 CST 2018
     */
    public void setStrongUpt(Integer strongUpt) {
        this.strongUpt = strongUpt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column c_app_version.download_url
     *
     * @return the value of c_app_version.download_url
     *
     * @mbg.generated Sun May 13 23:41:21 CST 2018
     */
    public String getDownloadUrl() {
        return downloadUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column c_app_version.download_url
     *
     * @param downloadUrl the value for c_app_version.download_url
     *
     * @mbg.generated Sun May 13 23:41:21 CST 2018
     */
    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl == null ? null : downloadUrl.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table c_app_version
     *
     * @mbg.generated Sun May 13 23:41:21 CST 2018
     */
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        CAppVersion other = (CAppVersion) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getVersionCode() == null ? other.getVersionCode() == null : this.getVersionCode().equals(other.getVersionCode()))
            && (this.getVersionName() == null ? other.getVersionName() == null : this.getVersionName().equals(other.getVersionName()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getAppSize() == null ? other.getAppSize() == null : this.getAppSize().equals(other.getAppSize()))
            && (this.getStrongUpt() == null ? other.getStrongUpt() == null : this.getStrongUpt().equals(other.getStrongUpt()))
            && (this.getDownloadUrl() == null ? other.getDownloadUrl() == null : this.getDownloadUrl().equals(other.getDownloadUrl()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table c_app_version
     *
     * @mbg.generated Sun May 13 23:41:21 CST 2018
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getVersionCode() == null) ? 0 : getVersionCode().hashCode());
        result = prime * result + ((getVersionName() == null) ? 0 : getVersionName().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getAppSize() == null) ? 0 : getAppSize().hashCode());
        result = prime * result + ((getStrongUpt() == null) ? 0 : getStrongUpt().hashCode());
        result = prime * result + ((getDownloadUrl() == null) ? 0 : getDownloadUrl().hashCode());
        return result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table c_app_version
     *
     * @mbg.generated Sun May 13 23:41:21 CST 2018
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", versionCode=").append(versionCode);
        sb.append(", versionName=").append(versionName);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", appSize=").append(appSize);
        sb.append(", strongUpt=").append(strongUpt);
        sb.append(", downloadUrl=").append(downloadUrl);
        sb.append("]");
        return sb.toString();
    }
}