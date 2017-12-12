package com.ally.northstar.bankapi.domain.model;

import io.crnk.core.resource.annotations.JsonApiId;

/**
 * Help me Obiwan, you're my only hope...
 * <p>
 * <p>
 *
 * @author bradharper
 * @version 2.0
 * @created 12/12/17 1:31 PM
 */
public abstract class Model {

    @JsonApiId
    protected Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
