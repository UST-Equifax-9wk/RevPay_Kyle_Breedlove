import { Component, inject } from '@angular/core';
import { Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
import { CurrentUserService } from '../current-user.service';
import { userObj } from '../remote.service';

@Component({
  selector: 'app-logout',
  standalone: true,
  imports: [],
  templateUrl: './logout.component.html',
  styleUrl: './logout.component.css'
})
export class LogoutComponent {
  router:Router
  currentuser:CurrentUserService
  constructor(router: Router,current:CurrentUserService){
    this.router=router;
    this.currentuser=current
    this.logout()
  }
  logout(){
    let cookie = document.cookie.split(';');
    let cookieParts = cookie[0].split('=');
    if(cookieParts[0]=="username"){
      let cookieService:CookieService=inject(CookieService);
      let noUser:userObj={
        username: 'none',
        phoneNumber: '',
        email: '',
        balance: 0,
        password: '',
        admin: false,
        business: false
      }
      cookieService.delete("username")
      this.currentuser.setCurrentUser(noUser)
      alert("Logged Out")
      this.router.navigate(["user-login"])
    }
  }
}
