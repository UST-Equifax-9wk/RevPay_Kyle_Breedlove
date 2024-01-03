import { Component } from '@angular/core';
import { RemoteService, userObj, Card } from '../remote.service';
import { CurrentUserService } from '../current-user.service';
import { HttpErrorResponse } from '@angular/common/http';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-add-card',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './add-card.component.html',
  styleUrl: './add-card.component.css'
})
export class AddCardComponent {
  cardNumber="";
  cvv="";
  expiration="";
  ownerName="";
  user:userObj;
  remote:RemoteService;
  router:Router;
  debit="";
  constructor(remote:RemoteService, currentUser:CurrentUserService, router:Router){
    this.remote=remote;
    this.user=currentUser.getCurrentUser();
    this.router=router;
    
  }

  



  addCard(){
    if(this.cardNumber==""||this.cvv==""||this.expiration==""||this.ownerName==""||this.debit==""||this.user.username=="none"){
      alert("Please fill out all fields")
    }
    //chech for chars returns true if contains chars that are non 0-9
    else if(this.checkForChars(this.cardNumber)||this.checkForChars(this.cvv)) alert("Non numeric characters in numeric only fields")
    else{
      let check=false;
      if(this.debit=="debit") check=true;
      else if(this.debit=="credit") check=false;
      else {
        alert("Issue with debit selection");
        return;
      }
      let newCard:Card={
        cardNumber: this.cardNumber,
        cvv: this.cvv,
        expiration: this.expiration,
        ownerName: this.ownerName,
        debit: check,
        user: this.user
      }
      this.remote.addCard(newCard).subscribe({
        next:(data)=>{
          alert("Success")
        },
        error: (error: HttpErrorResponse)=>{
          alert("Denied: Unable to add card")
        }
      })
    }
  }
  ngOnInit(){
    if(this.user.username=="none"){
      alert("You do not have access to this page, please log in")
      this.router.navigate(["user-login"])
      return;
    }
  }

  checkForChars(str: string):boolean{
   return /\D/.test(str)
  }
}
