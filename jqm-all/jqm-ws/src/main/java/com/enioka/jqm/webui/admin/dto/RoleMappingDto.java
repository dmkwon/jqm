package com.enioka.jqm.webui.admin.dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class RoleMappingDto
{
    private static final long serialVersionUID = 4328588095167173581L;
    private Integer roleId;
    private Integer profileId;

    public RoleMappingDto()
    {
        // JB convention.
    }

    public Integer getRoleId()
    {
        return roleId;
    }

    public void setRoleId(Integer role)
    {
        this.roleId = role;
    }

    public Integer getProfileId()
    {
        return profileId;
    }

    public void setProfileId(Integer profile)
    {
        this.profileId = profile;
    }
}
