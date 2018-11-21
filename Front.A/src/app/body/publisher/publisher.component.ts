import { Component, OnInit } from '@angular/core';
import {PublisherForm} from '../../services/jsonPattern/PublisherForm';
import {ErrorCode} from '../../services/jsonPattern/ErrorCode';
import {HttpParams, HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {HttpAssets} from '../../services/assets/HttpAssets';


@Component({
  selector: 'app-publisher',
  templateUrl: './publisher.component.html',
  styleUrls: ['./publisher.component.scss']
})
export class PublisherComponent implements OnInit {
  publisherForm: PublisherForm = {
    category: '4',
    title: '' ,
    content: ''
  };
  errCode: ErrorCode = {
    state: 0,
    message: 'nothing'
  };
  constructor(
    private http: HttpClient
  ) { }
  ask(): Observable<ErrorCode> {
    const httpParams: HttpParams = new HttpParams()
      .append('category', this.publisherForm.category)
      .append('title', this.publisherForm.title)
      .append('content', this.publisherForm.content)
    ;
    return this.http.post<ErrorCode>(
      'http://localhost:8080/publications/add',
      null,
      {
        params: {
          'category': this.publisherForm.category,
          'title': this.publisherForm.title,
          'content': this.publisherForm.content
        }
      }
    );
  }
  check() {
    this.ask().subscribe(errCode => this.errCode = errCode);
  }
  ngOnInit() {
  }

}
