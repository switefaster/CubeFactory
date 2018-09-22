package tk.dwcdn.switefaster.cubefactory.cable;

/**
 * @author switefaster
 */
public class UnionSearchSet<T> {

    private UnionSearchSet<T> parent;

    private T identifier;

    public UnionSearchSet(T identifier) {
        this.parent = this;
        this.identifier = identifier;
    }

    public UnionSearchSet<T> findRoot() {
        return (this.parent == this) ? this : (this.parent = this.parent.findRoot());
    }

    public UnionSearchSet<T> union(UnionSearchSet<T> anotherSet) {
        UnionSearchSet<T> myRoot = this.findRoot();
        UnionSearchSet<T> hisRoot = anotherSet.findRoot();
        if (myRoot != hisRoot) {
            myRoot.setParent(hisRoot);
        }
        return hisRoot;
    }

    public boolean isInSameSet(UnionSearchSet<T> anotherSet) {
        return this.findRoot() == anotherSet.findRoot();
    }

    public UnionSearchSet<T> getParent() {
        return parent;
    }

    public void setParent(UnionSearchSet<T> parent) {
        this.parent = parent;
    }

    public void reset() {
        this.parent = this;
    }

    public T getIdentifier() {
        return identifier;
    }

    public void setIdentifier(T identifier) {
        this.identifier = identifier;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof UnionSearchSet) {
            return ((UnionSearchSet) obj).identifier == this.identifier;
        }
        return false;
    }
}
