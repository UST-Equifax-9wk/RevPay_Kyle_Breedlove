import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterOutlet, RouterLink, Router } from '@angular/router';
import { RegisterUserComponent } from './register-user/register-user.component';
import { NavbarComponent } from './navbar/navbar.component';
import { UserLoginComponent } from './user-login/user-login.component';
import { FormsModule } from '@angular/forms';
import { CurrentUserService } from './current-user.service';
import { RemoteService, userObj } from './remote.service';
import { HttpErrorResponse } from '@angular/common/http';
//import { ShowTransactionsComponent } from './show-transactions/show-transactions.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, RouterOutlet, NavbarComponent, FormsModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'RevPayClient';
  currentUser:CurrentUserService;
  remote:RemoteService;
  router:Router;
  constructor(currentUser: CurrentUserService, remoteService:RemoteService, router:Router){
    this.currentUser=currentUser;
    this.remote=remoteService;
    this.router=router;

  }
  ngOnInit(){
    let cookie = document.cookie.split(';');
    let cookieParts = cookie[0].split('=');
    if(cookieParts[0]=="username"){
      this.getUserFromCookie(cookieParts[1]);
      this.router.navigate(["landing-page"])
    }
    else{
      this.router.navigate(["user-login"])
    }
  }
  getUserFromCookie(username:string){
    this.remote.getUserByUsername(username).subscribe({
      next:(data)=>{
        let user= data.body as userObj;
        this.currentUser.setCurrentUser(user);
      },
      error:(error:HttpErrorResponse)=>{
      }
    })
  }
}
