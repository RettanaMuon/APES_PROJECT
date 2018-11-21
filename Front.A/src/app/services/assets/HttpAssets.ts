import { HttpHeaders } from '@angular/common/http';

export class HttpAssets {
   private httpHeaders = {
    headers: new HttpHeaders({
      'Content-Type':  'application/json',
      'Authorization': 'my-auth-token'
    })
  };

  getHttpHeaders () { return this.httpHeaders; }

}
