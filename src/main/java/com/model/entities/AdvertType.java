package com.model.entities;

import javax.persistence.*;

@NamedQueries({
        @NamedQuery(
                name = "getTypeByName",
                query = "from AdvertType where typeName = :typeName"
        )
})

@Entity
@Table
public class AdvertType {
    @Override
    public String toString() {
        return "AdvertType{" +
                "typeId=" + typeId +
                ", typeName1='" + typeName + '\'' +
                '}';
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int typeId;

    @Column
    private String typeName;

    public AdvertType() {
    }

    public AdvertType(String typeName) {

        this.typeName = typeName;
    }

    public int getTypeId() {

        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName1(String typeName) {
        this.typeName = typeName;
    }
}
