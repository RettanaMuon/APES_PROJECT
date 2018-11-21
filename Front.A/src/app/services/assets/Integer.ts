export class Integer{
    n : number;
    isNull = false;

    Integer (n){
        if(n == null){
            this.isNull = true
            this.n = 0;
        } else {
            this.n = n;
        }
    }

    get () : number{
        if(this.isNull){
            return null;
        }else{
            return this.n;
        }
    }
}