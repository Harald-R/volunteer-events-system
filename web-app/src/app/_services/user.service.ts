import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';

import { environment } from '@environments/environment';
import { User } from '@app/_models';
import {map} from 'rxjs/operators';
import {getResponseURL} from '@angular/http/src/http_utils';

@Injectable({ providedIn: 'root' })
export class UserService {
    constructor(private http: HttpClient) { }

    getAll() {
        return this.http.get<User[]>(`${environment.apiUrl}/api/users`, {headers: new HttpHeaders('Content-Type: application/x-www-form-urlencoded')});
    }

    getById(id: number) {
        return this.http.get(`${environment.apiUrl}/api/users/${id}`, {headers: new HttpHeaders('Content-Type: application/x-www-form-urlencoded')});
    }

    register(user: User) {
      const name = user.name;
      const email = user.email;
      const password = user.password;
      const type = 2;
      const c = 'name=' + name + '&email=' + email + '&password=' + password + '&type=' + type;
      console.log(c);
      return this.http.post('http://localhost:3000/api/auth/register', JSON.stringify({name, email, password, type}), {headers: new HttpHeaders().set('Content-Type', 'application/x-www-form-urlencoded')});

    }
    update(user: User) {
        return this.http.put(`${environment.apiUrl}/users/${user.id}`, user);
    }

    delete(id: number) {
        return this.http.delete(`${environment.apiUrl}/api/users/${id}`,{headers: new HttpHeaders('Content-Type: application/x-www-form-urlencoded')});
    }
}
