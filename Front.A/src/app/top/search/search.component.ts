import { Component, OnInit } from '@angular/core';
import {$} from "protractor";

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.scss']
})
export class SearchComponent implements OnInit {
  query : string;
  constructor() { }

  ngOnInit() {
  }
}

