/**
 * Copyright © 2013 enioka. All rights reserved
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.enioka.jqm.webui.admin.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import com.enioka.jqm.jpamodel.RUser;

@XmlRootElement
public class PemissionsBagDto implements Serializable
{
    private static final long serialVersionUID = 5756796033393025919L;

    @XmlElementWrapper(name = "profiles")
    @XmlElement(name = "profile", type = PermissionsInProfileDto.class)
    public List<PermissionsInProfileDto> profiles = new ArrayList<PermissionsInProfileDto>();

    public String login, freeText;
    public Integer id;

    public PermissionsInProfileDto getProfile(Integer id)
    {
        for (PermissionsInProfileDto d : profiles)
        {
            if (d.id.equals(id))
            {
                return d;
            }
        }
        return null;
    }

    public PemissionsBagDto(RUser u)
    {
        this.login = u.getLogin();
        this.freeText = u.getFreeText();
        this.id = u.getId();
    }

    PemissionsBagDto()
    {}
}
