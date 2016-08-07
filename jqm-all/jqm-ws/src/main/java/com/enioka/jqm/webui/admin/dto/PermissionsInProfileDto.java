package com.enioka.jqm.webui.admin.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import com.enioka.jqm.jpamodel.Profile;

@XmlRootElement
public class PermissionsInProfileDto implements Serializable
{
    private static final long serialVersionUID = -2855846362312318064L;

    public Integer id;
    public String name;

    @XmlElementWrapper(name = "permissions")
    @XmlElement(name = "permission", type = String.class)
    public List<String> permissions = new ArrayList<String>();

    PermissionsInProfileDto()
    {

    }

    public PermissionsInProfileDto(Profile p)
    {
        this.id = p.getId();
        this.name = p.getName();
    }
}
