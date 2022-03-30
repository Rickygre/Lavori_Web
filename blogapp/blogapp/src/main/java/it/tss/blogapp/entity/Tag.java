
package it.tss.blogapp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author tss
 */
@Entity
@Table(name = "tag")
public class Tag extends BaseEntity {

    @Column(nullable = false)
    private String name;

    public Tag(){
        
    }
    
    public Tag(String tag) {
        this.name=tag;
    }

    /*
    getter setter
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Tag{" + "id=" + id + ", name=" + name + '}';
    }

    
    
}
