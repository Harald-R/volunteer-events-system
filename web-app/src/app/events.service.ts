import { Injectable } from '@angular/core';
import {Observable} from 'rxjs';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {IEvent} from '@app/_models/event';
import { AuthenticationService } from '@app/_services';

@Injectable({
  providedIn: 'root'
})

export class EventsService {
  private url = 'http://localhost:3000/api/events';

  constructor(private http: HttpClient,
    private authenticationService: AuthenticationService) {

  }


  getEvents(): Observable<Array<IEvent>> {
    return this.http.get<Array<IEvent>>(this.url, {headers: new HttpHeaders('Content-Type: application/x-www-form-urlencoded')});
  }

  addEvent(event: IEvent) {
    const name = event.name;
    const description = event.description;
    const location = event.location;
    const date = event.date;
    const c = 'name=' + name + '&date=' + date + '&description=' + description + '&location=' + location;
    console.log(c);

    const token = this.authenticationService.currentUserValue.token;
    console.log(token);

    return this.http.post(this.url, c, {
      headers: new HttpHeaders ({
        'Content-Type': 'application/x-www-form-urlencoded',
        'x-access-token': token
      })
    });
  }

  getEvent(id: number): Observable<IEvent> {

    const url = this.url + '/' + id;
    return this.http.get<IEvent>(url, {headers: new HttpHeaders('Content-Type: application/x-www-form-urlencoded')});
  }

  updateEvent(event: IEvent): Observable<IEvent> {
    const name = event.name;
    const description = event.description;
    const location = event.location;
    const date = event.date;

    const token= this.authenticationService.currentUserValue.token;
    const url = this.url + '/' + event._id;
    const c = 'name=' + name + '&description=' + description + '&location=' + location + '&date' + date;

    return this.http.put<IEvent>(url, c, {headers: new HttpHeaders({
      'Content-Type': 'application/x-www-form-urlencoded',
      ' x-access-token': `${token}`
      })
    });
  }

}
