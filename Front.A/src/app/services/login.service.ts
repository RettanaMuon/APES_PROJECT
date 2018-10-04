import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from "@angular/common/http";
import { LoginForm } from "./jsonPattern/LoginForm";
import { Observable, of} from "rxjs";
import {ErrorCode} from "./jsonPattern/ErrorCode";
import {get} from "http";
import {post} from "selenium-webdriver/http";


@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(
      private http : HttpClient
  ) { }

  ask(form : LoginForm) : Observable<ErrorCode>{
    var httpParams : HttpParams = new HttpParams()
        .append("username" , form.username)
        .append("password" , form.password
    )
    return this.http.get<ErrorCode>("http://localhost:8080/login" ,{params : httpParams});
  }

}
