package CMSProject.core.nodes;

import CMSProject.core.Collection;

import java.util.ArrayList;

public abstract class Node<T> extends Collection {
    private Node<T> parent;
    private ArrayList<Node<T>> childs;
    private T data;

    public Node (Node<T> parent, ArrayList<Node<T>> childs, T data){
        this.parent = parent;
        this.childs = childs;
        this.data = data;
    }

    public Node (Node<T> parent, T data){
        this(parent, new ArrayList<Node<T>>(), data);
    }

    public Node (T data){
        this(null, data);
    }

    public Node<T> getParent(){
        return parent;
    }

    public ArrayList<Node<T>> getChilds(){
        return childs;
    }

    public T getData(){
        return data;
    }

    public void setParent(Node<T> parent){ this.parent = parent; }
    public void setData (T data){ this.data = data; }

}
