import {Asset} from "./Asset";

export class _IDNode<T extends Asset>{
    _id;
    parents : number;
    childs : Array<number>;
    data : T;
}