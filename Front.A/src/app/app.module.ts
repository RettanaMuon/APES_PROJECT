import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';



import { AppComponent } from './app.component';
import { TopComponent } from './top/top.component';
import { BodyComponent } from './body/body.component';
import { FooterComponent } from './footer/footer.component';
import { DashComponent } from './top/dash/dash.component';
import { SnsComponent } from './top/sns/sns.component';
import { CopyrightComponent } from './footer/copyright/copyright.component';
import { SideComponent } from './body/side/side.component';
import { SearchComponent } from './top/search/search.component';
import { LogComponent} from './top/log/log.component';
import { PublisherComponent } from './body/publisher/publisher.component';
import { PublicationsComponent } from './body/publications/publications.component';
import { InstallComponent } from './install/install.component';
import { RouterModule } from '@angular/router';
import { RootComponent } from './root/root.component';

const appRoutes = [
  { path : 'install', component: InstallComponent},
  { path : '', component : RootComponent}
];

@NgModule({
  declarations: [
      AppComponent,
      TopComponent,
      BodyComponent,
      FooterComponent,
      DashComponent,
      SnsComponent,
      CopyrightComponent,
      SideComponent,
      SearchComponent,
      LogComponent,
      PublisherComponent,
      PublicationsComponent,
      InstallComponent,
      RootComponent,
  ],
  imports: [
    RouterModule.forRoot(
      appRoutes,
      { enableTracing: true }
    ),
    BrowserModule,
    NgbModule.forRoot(),
      FormsModule,
      HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
