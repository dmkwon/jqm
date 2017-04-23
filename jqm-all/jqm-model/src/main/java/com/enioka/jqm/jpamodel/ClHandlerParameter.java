package com.enioka.jqm.jpamodel;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <strong>Not part of any API - this an internal JQM class and may change without notice.</strong> <br>
 * JPA persistence class for storing the parameters of {@link ClHandler}s.
 */
@Entity
@Table(name = "ClHandlerParameter")
public class ClHandlerParameter implements Serializable
{
    private static final long serialVersionUID = 7803057784162464423L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false, length = 50, name = "KEYNAME")
    private String key;

    @Column(nullable = false, length = 100, name = "VALUE")
    private String value;

    /**
     * A technical ID without any meaning. Generated by the database.
     */
    public Integer getId()
    {
        return id;
    }

    /**
     * See {@link #getId()}
     */
    public void setId(Integer id)
    {
        this.id = id;
    }

    /**
     * The name of the parameter.<br>
     * Max length is 50.
     */
    public String getKey()
    {
        return key;
    }

    /**
     * See {@link #getKey()}
     */
    public void setKey(String key)
    {
        this.key = key;
    }

    /**
     * Value of the parameter.<br>
     * Max length is 100.
     */
    public String getValue()
    {
        return value;
    }

    /**
     * See {@link #getValue()}
     */
    public void setValue(String value)
    {
        this.value = value;
    }
}