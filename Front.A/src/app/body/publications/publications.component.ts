import { Component, OnInit } from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {Publication} from '../../services/jsonPattern/Publication';
import {Observable} from 'rxjs';

@Component({
  selector: 'app-publications',
  templateUrl: './publications.component.html',
  styleUrls: ['./publications.component.scss']
})
export class PublicationsComponent implements OnInit {
  publications: Publication[] = [];

  constructor(
    private http: HttpClient
  ) { }

  ngOnInit() {
    this.getPublication();
  }

  ask(): Observable<Publication[]> {
    return this.http.get<Publication[]>(
      'http://localhost:8080/publications/getAll'
    );
  }
  getPublication() {
    this.ask().subscribe(subs => this.publications = subs);
  }

  getComments() {

  }
}
