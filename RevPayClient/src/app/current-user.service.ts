import { Injectable } from '@angular/core';
import { userObj } from './remote.service';
@Injectable({
  providedIn: 'root'
})
export class CurrentUserService {
  username:string;
  email:string;
  phoneNumber:string;
  balance:number;
  admin:boolean;
  business:boolean;
  constructor() { 
    this.username = "none";
    this.email = "";
    this.phoneNumber = "";
    this.balance = 0;
    this.admin = false;
    this.business = false;
  }
  getUsername(): string{
    return this.username;
  }


  setCurrentUser(user:userObj){
    this.username=user.username;
    this.email=user.email;
    this.phoneNumber=user.phoneNumber;
    this.balance=user.balance;
    this.admin=user.admin;
    this.business=user.business;
  }
  getCurrentUser():userObj{
    let user:userObj={
      username:this.username,
      email:this.email,
      phoneNumber:this.phoneNumber,
      password:"Hidden",
      balance:this.balance,
      admin:this.admin,
      business:this.business
    }
    return user;
  }

}
