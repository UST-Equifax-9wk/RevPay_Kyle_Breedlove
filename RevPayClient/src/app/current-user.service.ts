import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class CurrentUserService {

  constructor() { }
  username = "temp";
  email = "";
  phoneNumber = "";
  isAdmin = false;
  isBusiness = false;

  getUsername(): string{
    return this.username;
  }

  setUsername(username:string){
    this.username=username;
    console.log("username: [" + username + " | " + this.username + "] in service");
  }

}
