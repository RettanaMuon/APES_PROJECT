package com.apesdev.S.core.nodes;

import com.apesdev.S.core.Collection;
import com.apesdev.S.core.assets.Asset;
import com.apesdev.S.core.assets.Link;
import com.apesdev.S.core.counter.Counter;
import com.apesdev.S.core.database.DB;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

import javax.validation.constraints.NotNull;

import static com.apesdev.S.core.parameter.Indexes.*;
import static com.mongodb.client.model.Updates.*;

import java.util.ArrayList;

public class _IDNode <T extends Object> extends Collection {
    private Integer parent;
    private Integer _id = null;
    private ArrayList<Integer> childs;
    @NotNull
    private Object data;
    private String counterName;
    private String name;

    public _IDNode (Integer parent, ArrayList<Integer> childs, Object data, String counterName){
        this.name = null;
        if (parent == -1)
            this.parent = null;
        else
            this.parent = parent;
        if (childs.isEmpty())
            this.childs = new ArrayList<>();
        else
            this.childs = childs;
        this.data = data;
        this.counterName = counterName;
        this._id = Counter.increment(getCounterName());
    }
    public _IDNode (Integer parent, Object data, String counterName){
        this(parent, new ArrayList<Integer>(), data, counterName);
    }
    public _IDNode (Object data, String counterName){
        this((Integer)null, data, counterName);
    }    public _IDNode (String name, Integer parent, ArrayList<Integer> childs, Object data, String counterName){
        this.name = name;
        this.parent = parent;
        this.childs = childs;
        this.data = data;
        this.counterName = counterName;
        this._id = Counter.increment(getCounterName());
    }

    public _IDNode (String name, Integer parent, Object data, String counterName){
        this(name, parent, new ArrayList<Integer>(), data, counterName);
    }
    public _IDNode (String name, Object data, String counterName){
        this(name, null, data, counterName);
    }
    public _IDNode(String collection, int _id) {
        if (exist(collection, _id)){
            Document doc = (Document) DB.getCollection(collection).find(new Document(_ID, _id)).first();
            this.name = doc.getString(I_NAME);
            this.parent = doc.getInteger(I_PARENTS);
            this._id = doc.getInteger(_ID);
            this.childs = (ArrayList<Integer>) doc.get(I_CHILDS);
            //this.data = new  NodeData<T>().generate((Document) doc.get(I_DATA));
            this.data = doc.get(I_DATA);
            this.counterName = "";
        }
    }

    public Integer getParent(){
        return parent;
    }
    public ArrayList<Integer> getChilds(){
        return childs;
    }
    public String getCounterName(){
        return counterName;
    }
    public Object getData(){
        return data;
    }
    public Integer get_id() {
        return _id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setParent(Integer parent){ this.parent = parent; }
    public void setData (Document data){ this.data = data; }
    public void set_id(Integer _id) {
        this._id = _id;
    }

    public boolean exist(String collection , Integer _id){
        return DB.exist(collection, new Document(_ID,_id));
    }
    private boolean isNull(String s){
        if((s == null ) || (s == ""))
            return true;
        return false;
    }

    public static Document remove(String collection , Integer _id){
        MongoCollection c = DB.getCollection(collection);
        Document query = new Document(_ID,_id);
        Document docToRemove = (Document) c.find(query).first();
        c.findOneAndUpdate(
                new Document(_ID, docToRemove.getInteger(I_PARENTS)),
                pull(I_CHILDS, docToRemove.get(_ID))
        );
        c.updateMany(
                new Document(_ID, new Document ("$in", (ArrayList<Integer>) docToRemove.get(I_CHILDS))),
                set(I_PARENTS, null)
        );
        c.deleteOne(docToRemove);
        return docToRemove;
    }

    @Override
    public _IDNode<T> insert(String collection) {
        if (getParent() != null){
            insertIntoTree(collection);
        }
        if (!DB.exist(collection, new Document(_ID,_id)))
            super.insert(collection);
        else
            DB.getCollection(collection).findOneAndReplace(new Document(_ID,_id), toDocument());
        return this;
    }

    public void insertIntoTree(String collection) {
        MongoCollection c = DB.getCollection(collection);
        if(getParent() != null){
            if (exist(collection,getParent())) {
                Document query = new Document(_ID, getParent());
                c.findOneAndUpdate(
                        query,
                        addToSet(I_CHILDS, _id)
                );
            }
        }
        if(!getChilds().isEmpty()){
            for (Integer childId : getChilds()){
                if (exist(collection, childId)){
                    Document query = new Document(_ID, childId);
                    c.findOneAndUpdate(
                            query,
                            set(I_PARENTS, _id)
                    );
                }
            }
        }
    }

    public static void initializeDatabase(String collection, boolean withName){
        MongoCollection d = DB.getCollection(collection);
        d.createIndex(new Document(I_DATA,1));
        d.createIndex(new Document(I_PARENTS,1));
        d.createIndex(new Document(I_CHILDS,1));
        if(withName)
            d.createIndex(new Document(I_NAME,1));
    }

    public static void initializeDatabase(String collection) {
        initializeDatabase(collection, false);
    }

    @Override
    public Document toDocument() {
        Document res = new Document()
                .append(_ID, get_id())
                .append(I_DATA, getData())
                .append(I_PARENTS, getParent())
                .append(I_CHILDS, getChilds())
                ;
        if (!isNull(name))
            res.append(I_NAME, name);
        return res;
    }

    public Document toRestDocument(String collection){
        ArrayList<Document> childRes = new ArrayList<>();
        for(Integer childId : getChilds()){
            childRes.add(new _IDNode<Link>(collection,childId).toRestDocument(collection));
        }
        Document res = new Document()
                .append(_ID, get_id())
                .append(I_DATA, getData())
                .append(I_PARENTS, getParent())
                .append(I_CHILDS, getChilds())
                ;
        if (!isNull(name))
            res.append(I_NAME, name);
        return res;
    }

}
