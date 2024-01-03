import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse,HttpClientModule, HttpHeaders } from '@angular/common/http';
import { ObjectUnsubscribedError, Observable, Subscription } from 'rxjs';
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

  login(username:string,password:string):Observable<HttpResponse<Object>>{
    let auth: Auth = new Auth;
    auth.username=username;
    auth.password=password;
    console.log("auth stringify "+JSON.stringify(auth))
    return this.http.post(this.baseUrl+"/login", JSON.stringify(auth),
      {observe:'response',
      withCredentials:true,
        headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })})
  }

  registerUser(user:userObj):Observable<HttpResponse<Object>>{
    return this.http.post(this.baseUrl+"/register", JSON.stringify(user),
      {observe:'response',
        withCredentials:true,
        headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })})
  }


  getUserByUsername(username:string):Observable<HttpResponse<Object>>{
    let response = this.http.get(this.baseUrl+"/users/username/"+username,
      {observe:'response',
        withCredentials:true,
        headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })}
    )    
    return response;
  }
  getUserByEmail(email:string):Observable<HttpResponse<Object>>{
    let response = this.http.get(this.baseUrl+"/users/email/"+email,
      {observe:'response',
        withCredentials:true,
        headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })}
    )    
    return response;
  }
  getUserByPhoneNumber(phone:string):Observable<HttpResponse<Object>>{
    let response = this.http.get(this.baseUrl+"/users/phone-number/"+phone,
      {observe:'response',
        withCredentials:true,
        headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })}
    )    
    return response;
  }
  getCards(username:string):Observable<HttpResponse<Object>>{
    let response = this.http.get(this.baseUrl+"/cards/user/"+username,
      {observe:'response',
        withCredentials:true,
        headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })}
    )    
    return response;
  }
  createTransaction(transaction:TransactionObj):Observable<HttpResponse<Object>>{
    return this.http.post(this.baseUrl+"/transaction", JSON.stringify(transaction),
      {observe:'response',
        withCredentials:true,
        headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })}
    )
  }
  getTransactions(username:string):Observable<HttpResponse<Object>>{
    return this.http.get(this.baseUrl+"/transactions/"+username,
    {observe:'response',
    withCredentials:true,
    headers: new HttpHeaders({
    'Content-Type': 'application/json'
  })})
  }

  getLoan(username:string):Observable<HttpResponse<Object>>{
    return this.http.get(this.baseUrl+"/loan/user/"+username, {observe:'response',
    withCredentials:true,
    headers: new HttpHeaders({
    'Content-Type': 'application/json'
  })} )
  }

  createSecurityQuestion(question:SecurityQuestion):Observable<HttpResponse<Object>>{
    return this.http.post(this.baseUrl+"/security-question", JSON.stringify(question),
      {observe:'response',
      withCredentials:true,
      headers: new HttpHeaders({
      'Content-Type': 'application/json'
      })}
    )
  }

  getSecurityQuestion(username:string):Observable<HttpResponse<Object>>{
    return this.http.get(this.baseUrl+"/security-question/user/"+username,
    {observe:'response',
    withCredentials:true,
    headers: new HttpHeaders({
    'Content-Type': 'application/json'
  })})
  }

  updatePassword(user:userObj):Observable<HttpResponse<Object>>{
    return this.http.post(this.baseUrl+"/user/change-password", JSON.stringify(user),
    {observe:'response',
      withCredentials:true,
      headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })})
  }
  addCard(card:Card):Observable<HttpResponse<Object>>{
    return this.http.post(this.baseUrl+"/cards", JSON.stringify(card),
    {observe:'response',
    withCredentials:true,
    headers: new HttpHeaders({
    'Content-Type': 'application/json'
  })}
    )
  }
  requestBusinessAccount(business:Business):Observable<HttpResponse<Object>>{
    return this.http.post(this.baseUrl+"/email/request-business", JSON.stringify(business),
    {observe:'response',
    withCredentials:true,
    headers: new HttpHeaders({
    'Content-Type': 'application/json'
  })}
    )
  }
  requestPayment(user:userObj, username:string, amount:string):Observable<HttpResponse<Object>>{
    return this.http.post(this.baseUrl+"/email/request-payment/"+username+"/"+amount, JSON.stringify(user),
    {observe:'response',
    withCredentials:true,
    headers: new HttpHeaders({
    'Content-Type': 'application/json'
  })}
    )
  }
  requestLoan(user:userObj, amount:string):Observable<HttpResponse<Object>>{
    return this.http.post(this.baseUrl+"/email/request-loan/"+amount, JSON.stringify(user),
    {observe:'response',
    withCredentials:true,
    headers: new HttpHeaders({
    'Content-Type': 'application/json'
  })}
    )
  }
} 
export interface Business{
  businessName:string;
  businessAddress:string;
  user:userObj;
}
export interface SecurityQuestion{
  securityQuestion:string;
  securityAnswer:string;
  user:userObj;
}
export interface Loan{
  balance:number;
  creationDate:Date;
  interestRate:number;
  lastInterestUpdate:Date;
  lastPaymentDate:Date;
  minimumPayment:number;
  paymentDueDay:number;
  user:userObj;
}
export interface TransactionObj{
  transactionId:number;
  cost:number;
  date:Date;
  card:Card;
  payee:userObj;
  payer:userObj;
  payWithBalance:boolean;
}
export interface Card{
  cardNumber:string;
  cvv:string;
  expiration:string;
  ownerName:string;
  debit:boolean;
  user:userObj;
}
export class Auth{
  username:string="";
  password:string="";
}
export interface userObj{
  username:string;
  phoneNumber:string;
  email:string;
  balance:number;
  password:string;
  admin:boolean;
  business:boolean;
}
