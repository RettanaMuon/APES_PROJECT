import { Component, OnInit } from '@angular/core';
import {LoginService} from "../../services/login.service";
import {ErrorCode} from "../../services/jsonPattern/ErrorCode";
import {LoginForm} from "../../services/jsonPattern/LoginForm";
import {Observable} from "rxjs/internal/Observable";

@Component({
  selector: 'app-log',
  templateUrl: './log.component.html',
  styleUrls: ['./log.component.scss']
})
export class LogComponent implements OnInit {
     loginForm: LoginForm = {
         username : '',
         password : ''
     };
     errCode: ErrorCode = {
         state : 0,
         message : ''
     };

  constructor(
      private loginService: LoginService
  ) { }

  ngOnInit() {
  }

  check() : void{
      this.loginService.ask(this.loginForm)
          .subscribe(errCode => this.errCode = errCode);
  }

}
