import { Component, OnInit } from '@angular/core';
import { BannerService } from "../services/banner.service";

@Component({
  selector: 'app-top',
  templateUrl: './top.component.html',
  styleUrls: ['./top.component.scss']
})
export class TopComponent implements OnInit {

  constructor(
      private banner : BannerService
  ) { }

  ngOnInit() {
  }

  getBannerUrl() : string{ return this.banner.getUrl(); }
  getBannerMaxHeight() : number { return this.banner.getMaxHeight();}
  getBannerMaxWidth() : number { return this.banner.getMaxWidth(); }
}
