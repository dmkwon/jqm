/**
 * Copyright © 2016 enioka. All rights reserved
 * Authors: Marc-Antoine GOUILLART (marc-antoine.gouillart@enioka.com)
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

package com.enioka.jqm.jpamodel;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * <strong>Not part of any API - this an internal JQM class and may change without notice.</strong> <br>
 * JPA persistence class for assigning one {@link RRole} to a {@link RUser}.
 */
@Entity
@Table(name = "RoleAssignment")
public class RUserRoleAssignment implements Serializable
{
    private static final long serialVersionUID = 7024239948795469680L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    private Profile profile = null;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private RUser user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private RRole role;

    private Boolean global = false;

    /**
     * Technical ID without interest as this is a M2M intermediary class.
     */
    int getId()
    {
        return id;
    }

    /**
     * See {@link #getId()}
     */
    void setId(int id)
    {
        this.id = id;
    }

    /**
     * The profile this assignment is limited to. May be null if {@link #isGlobal()} is true (which means the assignment is for all
     * profiles)
     */
    public Profile getProfile()
    {
        return profile;
    }

    /**
     * See {@link #getProfile()}
     */
    public void setProfile(Profile profile)
    {
        this.profile = profile;
    }

    /**
     * The user being assigned a role.
     */
    public RUser getUser()
    {
        return user;
    }

    /**
     * See {@link #getUser()}
     */
    public void setUser(RUser user)
    {
        this.user = user;
    }

    /**
     * The {@link RRole} being assigned to the user.
     */
    public RRole getRole()
    {
        return role;
    }

    /**
     * See {@link #getRole()}
     */
    public void setRole(RRole role)
    {
        this.role = role;
    }

    /**
     * True if the assignment is not related to a particular {@link RRole} but to all of them (including future ones). If true,
     * {@link #getProfile()} is ignored (and may be null). <br>
     * Default is false.
     */
    public Boolean isGlobal()
    {
        return global;
    }

    /**
     * See {@link #isGlobal()}
     */
    public void setGlobal(Boolean global)
    {
        this.global = global;
    }
}
