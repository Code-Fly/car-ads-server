package com.cloud.carads.area.entity;

public class TAreainfo {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_d_areainfo.id
     *
     * @mbg.generated Fri May 11 23:05:56 CST 2018
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_d_areainfo.name
     *
     * @mbg.generated Fri May 11 23:05:56 CST 2018
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_d_areainfo.arealevel
     *
     * @mbg.generated Fri May 11 23:05:56 CST 2018
     */
    private Byte arealevel;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_d_areainfo.parent_id
     *
     * @mbg.generated Fri May 11 23:05:56 CST 2018
     */
    private Integer parentId;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_d_areainfo.id
     *
     * @return the value of t_d_areainfo.id
     *
     * @mbg.generated Fri May 11 23:05:56 CST 2018
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_d_areainfo.id
     *
     * @param id the value for t_d_areainfo.id
     *
     * @mbg.generated Fri May 11 23:05:56 CST 2018
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_d_areainfo.name
     *
     * @return the value of t_d_areainfo.name
     *
     * @mbg.generated Fri May 11 23:05:56 CST 2018
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_d_areainfo.name
     *
     * @param name the value for t_d_areainfo.name
     *
     * @mbg.generated Fri May 11 23:05:56 CST 2018
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_d_areainfo.arealevel
     *
     * @return the value of t_d_areainfo.arealevel
     *
     * @mbg.generated Fri May 11 23:05:56 CST 2018
     */
    public Byte getArealevel() {
        return arealevel;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_d_areainfo.arealevel
     *
     * @param arealevel the value for t_d_areainfo.arealevel
     *
     * @mbg.generated Fri May 11 23:05:56 CST 2018
     */
    public void setArealevel(Byte arealevel) {
        this.arealevel = arealevel;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_d_areainfo.parent_id
     *
     * @return the value of t_d_areainfo.parent_id
     *
     * @mbg.generated Fri May 11 23:05:56 CST 2018
     */
    public Integer getParentId() {
        return parentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_d_areainfo.parent_id
     *
     * @param parentId the value for t_d_areainfo.parent_id
     *
     * @mbg.generated Fri May 11 23:05:56 CST 2018
     */
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_d_areainfo
     *
     * @mbg.generated Fri May 11 23:05:56 CST 2018
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
        TAreainfo other = (TAreainfo) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getArealevel() == null ? other.getArealevel() == null : this.getArealevel().equals(other.getArealevel()))
            && (this.getParentId() == null ? other.getParentId() == null : this.getParentId().equals(other.getParentId()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_d_areainfo
     *
     * @mbg.generated Fri May 11 23:05:56 CST 2018
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getArealevel() == null) ? 0 : getArealevel().hashCode());
        result = prime * result + ((getParentId() == null) ? 0 : getParentId().hashCode());
        return result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_d_areainfo
     *
     * @mbg.generated Fri May 11 23:05:56 CST 2018
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", arealevel=").append(arealevel);
        sb.append(", parentId=").append(parentId);
        sb.append("]");
        return sb.toString();
    }
}