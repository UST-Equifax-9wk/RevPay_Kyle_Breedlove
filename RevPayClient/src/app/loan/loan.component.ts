import { Component } from '@angular/core';
import { RemoteService, Loan } from '../remote.service';
import { CurrentUserService } from '../current-user.service';
import { HttpErrorResponse } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { DayOfMonthPipe } from '../day-of-month.pipe';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
@Component({
  selector: 'app-loan',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './loan.component.html',
  styleUrl: './loan.component.css'
})
export class LoanComponent {
  remote:RemoteService;
  currentUser:CurrentUserService;
  username:string;
  loan:Array<Loan>=[];
  loanExists=false;
  loanAmount="";
  router:Router;
  constructor(remote:RemoteService,currentUser:CurrentUserService, router:Router){
    this.remote=remote;
    this.currentUser=currentUser;
    this.username=currentUser.getUsername();
    this.router=router
  }

  requestLoan(){
    if(this.loanAmount==""||this.checkForChars(this.loanAmount)) alert("Please insert a valid request amount");
    this.remote.requestLoan(this.currentUser.getCurrentUser(),this.loanAmount).subscribe({
      next:(data)=>{
            alert("We have sent your request to an admin for approval")
      },
      error:(error:HttpErrorResponse)=>{
        alert("Error Processing Your Request")
      }
    })
  }
  ngOnInit(){
    if(this.currentUser.username=="none"){
      alert("You do not have access to this page, please log in")
      this.router.navigate(["user-login"])
      return;
    }
    this.remote.getLoan(this.username).subscribe({
      next:(data)=>{
        this.loan[0] =data.body as Loan;
        this.loanExists=true;
      },
      error:(error:HttpErrorResponse)=>{

      }
    })
  }
  checkForChars(str: string):boolean{
    return /\D/.test(str)
   }
 
}
