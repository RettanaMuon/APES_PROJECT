import { Component, OnInit } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs/internal/Observable";
import {Link} from "../../services/jsonPattern/Link";
import {_IDNode} from "../../services/jsonPattern/_IDNode";
import {Dashboard} from "./Dashboard";
import {isNull} from "util";

@Component({
  selector: 'app-dash',
  templateUrl: './dash.component.html',
  styleUrls: ['./dash.component.scss']
})
export class DashComponent implements OnInit {

  dashboard : Dashboard = new Dashboard();

  constructor(private http : HttpClient) { }

  ngOnInit() {
    this.setDashboard();
  }

  getHttp (): Observable< Dashboard >{
    return this.http.get< Dashboard >("http://localhost:8080/dashboard");
  }

  setDashboard () : void{
      this.getHttp().subscribe(dashboard => this.dashboard = dashboard);
  }

  getChilds(id : number) : _IDNode<Link>[]{
    var res : _IDNode<Link>[] = [];
    var it = this.dashboard.dashboard.values();
    var child : _IDNode<Link>;
    while (child = it.next().value){
      if(isNull(id)){
        if (isNull(child.parents))
          res.push(child);
      }else if (id === child.parents){
        res.push(child);
      }
    }
    return res;
  }

  getRoot() : _IDNode<Link>[]{
    return this.getChilds(null);
  }

  isNull(n : number) : boolean{
    if (!n) return true;
    return false;
  }
}
