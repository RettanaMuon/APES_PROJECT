import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class BannerService {
  url : string;
  name : string;
  label : string;

  maxHeight : number;
  maxWidth : number;

  constructor() {
    this.url = "https://orig00.deviantart.net/6e09/f/2016/075/1/c/meguminsmall_by_suminoio-d9vdcy0.png";
    this.name = "banner";
    this.label = "banner";

    this.maxHeight = 50;
    this.maxWidth = 50;
  }

  getUrl() : string { return this.url; }
  getMaxHeight() : number{ return this.maxHeight;}
  getMaxWidth () : number{ return this.maxWidth; }
}
