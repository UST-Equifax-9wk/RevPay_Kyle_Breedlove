import { Injectable } from '@angular/core';
import { HttpClient,HttpClientModule, HttpHeaders } from '@angular/common/http';
import { Observable, Subscription } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class RemoteService {
  http:HttpClient;
  baseUrl = "http://localhost:8080"
  httpOptions = {
    observe: 'response'
  }
  constructor(httpClient:HttpClient) {
    this.http=httpClient;
   }
  login(username:string,password:string){
    return this.http.get(this.baseUrl+"/login/"+username+"/"+password,{observe:'response'})
  }
  get():any{
    console.log("start of get")
    let response = this.http.get(this.baseUrl+"/users/username/testUser")    
    let a: Object;
    // response.subscribe((data)=>a=data)
    return response;
    //let parseResponse = Object.assign(userObj,response)
    //console.log("parse from object,assign: "+parseResponse)
    //console.log("parse username: "+parseResponse.getUsername())
    // console.log("from response: "+response)
    // let t = JSON.parse(JSON.stringify(response));
    // console.log("after stringify: "+t)
    // return response;
  }
  
 
} 
// class userObj{
//   constructor(username:string, phoneNumber:string, email:string, balance:number, password:string, isAdmin:boolean, isBusiness:boolean){
//     this.username=username;
//     this.phoneNumber=phoneNumber;
//     this.email=email;
//     this.balance=balance;
//     this.password=password;
//     this.isAdmin=isAdmin;
//     this.isBusiness=isBusiness;
//   }
//   username:string;
//   phoneNumber:string;
//   email:string;
//   balance:number;
//   password:string;
//   isAdmin:boolean;
//   isBusiness:boolean;
//   getUsername(){
//     return this.username
//   }
// }
export interface Obj{
  value:string
}
export interface userObj{
  username:string;
  phoneNumber:string;
  email:string;
  balance:number;
  password:string;
  isAdmin:boolean;
  isBusiness:boolean;
}
