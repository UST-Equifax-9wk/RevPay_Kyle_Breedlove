import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CurrentUserService } from '../current-user.service';
import { Card, RemoteService, TransactionObj, userObj } from '../remote.service';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Router } from '@angular/router';

@Component({
  selector: 'app-create-transaction',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './create-transaction.component.html',
  styleUrl: './create-transaction.component.css'
})
export class CreateTransactionComponent {
  currentUser: CurrentUserService;
  remote:RemoteService;
  targetType=""
  total=0
  target=""
  paymentType=""
  selectedCard="";
  payWithUserBalance=false;
  cardArray:Array<Card> =[];
  username:string;
  router:Router;
  constructor(currentUser: CurrentUserService, remote:RemoteService, router:Router){
    this.currentUser=currentUser;
    this.remote=remote;
    this.username=currentUser.getUsername();
    this.router=router;
  }

  createTransaction(){

    if(this.targetType==""||(this.targetType!="loan"&&this.target=="")||this.total<=0||this.paymentType==""){
      alert("Denied: Must Fill Out All Fields")
      return;
    }
    if(this.paymentType=="card"){
      if(this.selectedCard==""){
        alert("Denied: Missing payment card");
        return;
      }
      else this.payWithUserBalance=false;
    }
    else if(this.paymentType=="balance"){
      if(this.currentUser.balance<this.total){
      alert("Denied: Not enough funds in balance, add funds or use card")
      return;
      }
      else this.payWithUserBalance=true;
    }
    if(this.targetType=="user"){
      this.remote.getUserByUsername(this.target).subscribe({
        next:(data)=>{
          let payeeUser:userObj = data.body as userObj;
          this.sendTransaction(payeeUser)
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
          this.sendTransaction(payeeUser)
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
          this.sendTransaction(payeeUser)
        },
        error:(error:HttpErrorResponse)=>{
          alert("Denied: Invalid Payment Target")
        }
      })
    }
    else if(this.targetType=="loan"){
      this.sendTransaction(this.currentUser.getCurrentUser());
    }

  }

  sendTransaction(payeeUser:userObj){

    let cardToAdd:Card={
      cardNumber: '',
      cvv: '',
      expiration: '',
      ownerName: '',
      debit: false,
      user: this.currentUser.getCurrentUser()
    };
    for(let card of this.cardArray){
      if(card.cardNumber==this.selectedCard) cardToAdd=card;
    }
    let transaction:TransactionObj={
      cost: this.total,
      card: cardToAdd,
      payee: payeeUser,
      payer: this.currentUser.getCurrentUser(),
      payWithBalance: this.payWithUserBalance,
      transactionId: 0,
      date: new Date()
    }
    this.remote.createTransaction(transaction).subscribe({
      next:(resp)=>{
        //updated information after transaction
        this.remote.getUserByUsername(this.currentUser.getUsername()).subscribe({
          next:(data)=>{
            let refreshUser = data.body as userObj;
            this.currentUser.setCurrentUser(refreshUser)
          }
        })
        alert("Success")
      },
      error:(error:HttpErrorResponse)=>{
        alert("Denied: Unable to make transaction")
      }
    })
  }
  ngOnInit(){
    if(this.currentUser.username=="none"){
      alert("You do not have access to this page, please log in")
      this.router.navigate(["user-login"])
      return;
    }
    this.remote.getCards(this.currentUser.username).subscribe({
      next:(data)=>{
        this.cardArray = data.body as Array<Card>;
      }
    })
  }

}
