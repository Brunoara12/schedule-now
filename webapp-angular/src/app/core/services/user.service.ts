import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private username: string = ""
  constructor() { }

  setUser(user: string){
    this.username = user
  }

  getUser() {
    return this.username
  }
}
