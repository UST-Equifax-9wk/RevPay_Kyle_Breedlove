import { Injectable } from '@angular/core';
import { userObj } from './remote.service';
@Injectable({
  providedIn: 'root'
})
export class CurrentUserService {

  constructor() { }
  username = "temp";
  email = "";
  phoneNumber = "";
  balance = 0
  isAdmin = false;
  isBusiness = false;

  getUsername(): string{
    return this.username;
  }

  setUsername(username:string){
    this.username=username;
    console.log("username: [" + username + " | " + this.username + "] in service");
  }

  setCurrentUser(user:userObj){
    this.username=user.username;
    this.email=user.email;
    this.phoneNumber=user.phoneNumber;
    this.balance=user.balance;
    this.isAdmin=user.isAdmin;
    this.isBusiness=user.isBusiness;
  }

}
