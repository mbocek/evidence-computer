package org.evidence.computer;

public abstract class AbstractEntity {

    public abstract Long getId();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
 
        if (o == null) {
            return false;
        }
        if (getClass() != o.getClass()) {
            return false;
        }
 
        AbstractEntity other = (AbstractEntity) o;
        return getId() != null &&
               getId().equals(other.getId());
    }
 
    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
