import { Component, OnInit } from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {ErrorCode} from '../services/jsonPattern/ErrorCode';

@Component({
  selector: 'app-install',
  templateUrl: './install.component.html',
  styleUrls: ['./install.component.scss']
})
export class InstallComponent implements OnInit {
  frontURL = '';
  backURL = '';
  mongodbURL = '';
  databaseName = '';
  errCode: ErrorCode = new ErrorCode();

  constructor(
    private http: HttpClient
  ) { }
  ngOnInit() {
  }
  createConfig() {
    const params: HttpParams = new HttpParams()
      .append('front_url', this.frontURL)
      .append('back_url', this.backURL)
      .append('database_host', this.mongodbURL)
      .append('database_name', this.databaseName);
    this.http.get<ErrorCode>('http://localhost:8080/install/createConfig', {params}).subscribe(errCode => this.errCode = errCode);
  }
}
