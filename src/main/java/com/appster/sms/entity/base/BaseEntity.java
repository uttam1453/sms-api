package com.appster.sms.entity.base;

import javax.persistence.*;

/**
 * created by atul on 05/01/17.
 */
@MappedSuperclass
public class BaseEntity extends AuditEntity {
    protected int id;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BaseEntity)) return false;
        BaseEntity that = (BaseEntity) o;
        return id == that.id;
    }

    @Override
    public String toString() {
        return
                "id=" + id;
    }

    @Override
    public int hashCode() {
        return com.google.common.base.Objects.hashCode(id);
    }
}
