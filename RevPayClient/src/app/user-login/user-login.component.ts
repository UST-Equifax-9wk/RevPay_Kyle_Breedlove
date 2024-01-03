import { Component } from '@angular/core';
import { RemoteService, userObj } from '../remote.service';
import { FormsModule } from '@angular/forms';
import { CurrentUserService } from '../current-user.service';
import { HttpErrorResponse } from '@angular/common/http';
import { JsonPipe } from '@angular/common';
import { CookieService } from 'ngx-cookie-service'
import { Router } from '@angular/router';
@Component({
  selector: 'app-user-login',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './user-login.component.html',
  styleUrl: './user-login.component.css'
})
export class UserLoginComponent {
  remote:RemoteService;
  currentUser:CurrentUserService;
  username="";
  password="";
  router:Router;
  constructor(remote:RemoteService,currentUser:CurrentUserService,router:Router){
    this.remote=remote;
    this.currentUser=currentUser;
    this.router=router;
  }
  tryLogin(){
    if(this.username==""||this.password==""){alert("Please insert both the username and password to log in")}
    else{
      this.remote.login(this.username,this.password)
      .subscribe({
        next: (data)=>{
          let user: userObj = data.body as userObj
          this.currentUser.setCurrentUser(user)
          let cookie = document.cookie.split(';');
          let cookieParts = cookie[0].split("=")
          this.router.navigate(["landing-page"])
        },
        error: (error: HttpErrorResponse) => {
          alert("Access Denied")

        }
      })
    }
 
  }
}
