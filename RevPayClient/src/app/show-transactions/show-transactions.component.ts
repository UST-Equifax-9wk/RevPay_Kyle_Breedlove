import { Component } from '@angular/core';
import { CurrentUserService } from '../current-user.service';
import { Business, RemoteService, TransactionObj } from '../remote.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { HttpErrorResponse } from '@angular/common/http';
@Component({
  selector: 'app-show-transactions',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './show-transactions.component.html',
  styleUrl: './show-transactions.component.css'
})
export class ShowTransactionsComponent {
  username:string;
  currentUser:CurrentUserService;
  remote:RemoteService;
  transactionArray:Array<TransactionObj>=[];
  balance:number;
  router:Router;
  businessName="";
  businessAddress="";
  constructor(currentUser:CurrentUserService, remote:RemoteService,router:Router){
    this.currentUser=currentUser;
    this.username = this.currentUser.getUsername();
    this.remote=remote;
    this.balance=currentUser.balance;
    this.router=router;
  }

  goToLoan(){
    this.router.navigate(["loan"])
  }

  requestBusiness(){
    if(this.businessName=="" || this.businessAddress==""){
      alert("Please insert a valid business name and address")
    }
    else{
      let business:Business={
        businessName: this.businessName,
        businessAddress: this.businessAddress,
        user: this.currentUser.getCurrentUser()
      }
      this.remote.requestBusinessAccount(business).subscribe({
        next:(data)=>{
          alert("We have sent your request to an administrator")
        },
        error:(error:HttpErrorResponse)=>{
          alert("Error processing your request")
        }
      })
    } 
  }

  ngOnInit(){
    if(this.currentUser.username=="none"){
      alert("You do not have access to this page, please log in")
      this.router.navigate(["user-login"])
      return;
    }
    this.remote.getTransactions(this.currentUser.getUsername()).subscribe((data)=>{
      this.transactionArray=data.body as Array<TransactionObj>;
      console.log(this.transactionArray)
    })
  }
  // ngDoCheck(){
  //   this.username=this.currentUser.getUsername();
  //   console.log("ngdoCheck show transactions");
  // }
}
