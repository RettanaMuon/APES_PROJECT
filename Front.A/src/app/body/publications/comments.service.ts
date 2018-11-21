import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class CommentsService {

  com: Comment;
  constructor(
    private http: HttpClient
  ) { }

  getComment(_id: number): Comment{
    const params: HttpParams = new HttpParams().append('_id', _id);
    this.http.get<Comment>('http://localhost:8080/comments/get', {params})
      .subscribe(com => this.com = com);
    return this.com;
  }
}
