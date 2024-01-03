import { HttpClient, HttpClientModule, HttpErrorResponse } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CurrentUserService } from '../current-user.service';
import { RemoteService, SecurityQuestion, userObj } from '../remote.service';
import { Router } from '@angular/router';
@Component({
  selector: 'app-register-user',
  standalone: true,
  imports: [FormsModule, HttpClientModule],
  templateUrl: './register-user.component.html',
  styleUrl: './register-user.component.css'
})
export class RegisterUserComponent {
  username = ""
  email = ""
  phoneNumber= ""
  password= ""
  confirmPassword= ""
  currentUser:CurrentUserService;
  remoteService:RemoteService;
  router:Router;
  securityQuestion="";
  securityAnswer="";
  constructor(currentUser:CurrentUserService, remote:RemoteService,router:Router){
    this.currentUser=currentUser;
    this.remoteService=remote;
    this.router=router;
  }
  
  OnClickSubmit(){
    if(this.username==""||this.password==""||this.phoneNumber==""||this.email==""||this.confirmPassword==""){
      alert("Denied: Must Complete All Fields");
      return;
    }
    if(this.checkForChars(this.phoneNumber)){
      alert("Non numeric character inserted in phone number, please only use 0-9")
    }
    if(this.password!=this.confirmPassword){
      alert("Denied: Password fields must match");
      return;
    }
    if(this.password.length<8){
      alert("Denied: Password too short");
      return;
    }
    if(this.username.length<4){
      alert("Denied: Username too short");
      return;
    }
    let newUser: userObj= {
      username: this.username,
      phoneNumber: this.phoneNumber,
      email: this.email,
      balance: 0,
      password: this.password,
      admin: false,
      business: false
    }
    this.remoteService.registerUser(newUser).subscribe(
      {
        next:(data) =>{
          let security:SecurityQuestion={
            securityQuestion:this.securityQuestion,
            securityAnswer:this.securityAnswer,
            user:newUser
          }
          this.remoteService.createSecurityQuestion(security).subscribe({
            next:(resp)=>{
              this.currentUser.setCurrentUser(newUser);
              this.router.navigate(["landing-page"])
            },
            error: (error:HttpErrorResponse)=>{
              if(error.status == 400) alert("Denied: Problem with security question input");
              else if(error.status == 409) alert("Denied: This Security Question already exists for this user");
              else alert("Denied: Unknown Error");
            }
          })
          
        },
        error: (error:HttpErrorResponse)=>{
          if(error.status == 400) alert("Denied: Problem with input");
          else if(error.status == 409) alert("Denied: Used Duplicate Username, Email, or Phone Number");
          else alert("Denied: Unknown Error");
        }
      }
    )

  }
  checkForChars(str: string):boolean{
    return /\D/.test(str)
   }
}
