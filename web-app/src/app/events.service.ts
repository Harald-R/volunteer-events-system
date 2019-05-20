import { Injectable } from '@angular/core';
import {Observable} from 'rxjs';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {IEvent} from '@app/_models/event';
@Injectable({
  providedIn: 'root'
})

export class EventsService {
  private url = 'http://localhost/3000/api/events';
  constructor(private http: HttpClient) { }

  getEvents(): Observable<Array<IEvent>> {
       return this.http.get<Array<IEvent>>(this.url, {headers: new HttpHeaders('Content-Type: application/x-www-form-urlencoded')});
  }

  addEvent(event: IEvent): Observable<IEvent> {
    const name = event.name;
    const descr = event.description;
    const loc = event.location;
    const date = event.date;
    return  this.http.post<IEvent>(this.url, { name , descr, loc, date}, { headers : new HttpHeaders('Content-Type: application/x-www-form-urlencoded, x-access-token: $event.token')});
  }
}
