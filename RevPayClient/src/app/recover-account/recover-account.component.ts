import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RemoteService, SecurityQuestion, userObj } from '../remote.service';
import { HttpErrorResponse } from '@angular/common/http';
import { CurrentUserService } from '../current-user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-recover-account',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './recover-account.component.html',
  styleUrl: './recover-account.component.css'
})
export class RecoverAccountComponent {
username=""
remote:RemoteService;
securityQuestions:Array<SecurityQuestion>=[];
cycleIndex=0;
length=this.securityQuestions.length
answer=""
correct=false;
password=""
confirmPassword=""
router:Router
user:userObj={
  username: '',
  phoneNumber: '',
  email: '',
  balance: 0,
  password: '',
  admin: false,
  business: false
};
constructor(remoteService:RemoteService, currentUser:CurrentUserService, router:Router){
  this.remote=remoteService;
  this.router=router;

}

retrieveUser(){
  this.remote.getUserByUsername(this.username).subscribe({
    next:(data)=>{
      this.user = data.body as userObj;
      this.getSecurityQuestions(this.user);

    },
    error:(error:HttpErrorResponse)=>{
      alert("No Matching Username Found")
    }
  })
}
getSecurityQuestions(user:userObj){
  this.remote.getSecurityQuestion(user.username).subscribe({
    next:(data)=>{
      this.securityQuestions=data.body as Array<SecurityQuestion>;
      length=this.securityQuestions.length

    },
    error: (error:HttpErrorResponse)=>{
      alert("Could not get security questions")
    }
  })
}
increaseCycleIndex(){
  this.cycleIndex++;
  if(this.cycleIndex>=this.securityQuestions.length)this.cycleIndex=0;
}
submitAnswer(){
  this.correct=(this.securityQuestions[this.cycleIndex].securityAnswer==this.answer)
}
changePassword(){
  if(this.password==""||this.confirmPassword==""){
    alert("Please Fill Out All Fields");
    return;
  }
  if(this.password!=this.confirmPassword){
    alert("Passwords Don't Match")
    return;
  }
  if(this.password.length<8){
    alert("Password too short");
    return;
  }
  this.user.password=this.password;
  this.remote.updatePassword(this.user).subscribe({
    next:(data)=>{
      alert("Success")
      this.router.navigate(["user-login"])
    },
    error: (error: HttpErrorResponse)=>{
      alert("Denied")
    }
  })
}
}
