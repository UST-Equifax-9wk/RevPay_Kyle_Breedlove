import { HttpErrorResponse } from '@angular/common/http';
import { Component } from '@angular/core';
import { Card, RemoteService, userObj } from '../remote.service';
import { CurrentUserService } from '../current-user.service';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-request-payment',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './request-payment.component.html',
  styleUrl: './request-payment.component.css'
})
export class RequestPaymentComponent {
  currentUser: CurrentUserService;
  remote:RemoteService;
  targetType=""
  total=0
  target=""

  username:string;
  router:Router;
  constructor(currentUser: CurrentUserService, remote:RemoteService, router:Router){
    this.currentUser=currentUser;
    this.remote=remote;
    this.username=currentUser.getUsername();
    this.router=router;
  }

  createTransaction(){

    if(this.targetType==""||(this.targetType!="loan"&&this.target=="")||this.total<=0){
      alert("Denied: Must Fill Out All Fields")
      return;
    }
    if(this.targetType=="user"){
      this.remote.getUserByUsername(this.target).subscribe({
        next:(data)=>{
          let payeeUser:userObj = data.body as userObj;
          this.sendRequest(payeeUser)
        },
        error:(error:HttpErrorResponse)=>{
          alert("Denied: Invalid Payment Target")
        }
      })
    }
    else if (this.targetType=="email"){
      this.remote.getUserByEmail(this.target).subscribe({
        next:(data)=>{
          let payeeUser:userObj = data.body as userObj;
          this.sendRequest(payeeUser)
        },
        error:(error:HttpErrorResponse)=>{
          alert("Denied: Invalid Payment Target")
        }
      })
    }
    else if (this.targetType=="phone"){
      this.remote.getUserByPhoneNumber(this.target).subscribe({
        next:(data)=>{
          let payeeUser:userObj = data.body as userObj;
          this.sendRequest(payeeUser)
        },
        error:(error:HttpErrorResponse)=>{
          alert("Denied: Invalid Payment Target")
        }
      })
    }
   

  }

  sendRequest(requestee:userObj){

    this.remote.requestPayment(requestee,this.currentUser.getUsername(),this.total+"").subscribe({
      next:(resp)=>{
        
        alert("Your request has been sent!")
      },
      error:(error:HttpErrorResponse)=>{
        alert("Denied: Unable to request transaction")
      }
    })
  }
  ngOnInit(){
    if(this.currentUser.username=="none"){
      alert("You do not have access to this page, please log in")
      this.router.navigate(["user-login"])
      return;
    }
  }
  checkForChars(str: string):boolean{
    return /\D/.test(str)
   }
}
